package com.login.read.write.file.jpg.png.myapplication.view.elaborateimage

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Environment
import android.os.ParcelFileDescriptor
import android.util.Log
import com.github.terrakok.cicerone.Router
import com.login.read.write.file.jpg.png.myapplication.utils.NAME_PNG_FILE
import com.login.read.write.file.jpg.png.myapplication.utils.resouces.ResourcesProvider
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject

class ElaborateImagePresenter @Inject constructor(
    private val router: Router,
    private val resourcesProviderImpl: ResourcesProvider
): MvpPresenter<ElaborateImageView>() {
    /** ИСХОДНЫЕ ДАННЫЕ */ //region
    // bitmap
    private var bitmap: Bitmap? = null
    // message
    private var message: String = ""
    // disposable
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    //endregion

    //region Установка навигации
    fun backPressed(): Boolean {
        router.exit()
        return true
    }
    //endregion

    /** Метод загрузки выбранной картики */
    fun loadAndShowImage(url: String) {
        loadingImage(url)
    }

    /** Загрузка jpg-картинки */ //region
    private fun loadingImage(selectedFile: String) {
        loadingImageCompletable(selectedFile)
            .doOnComplete {}
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                viewState.showToastLogMessage(message)
                bitmap?.let { bitmap ->
                    viewState.showImage(bitmap)
                }
            }, {
                Log.d("mylogs", "Ошибка загрузки jpg-файла: ${it.message}")
            })
    }

    private fun loadingImageCompletable(selectedFile: String
    ): Completable = Completable.create { emitter ->
        /** Загрузка картинки */
        bitmap = getBitmapFromUri(selectedFile, resourcesProviderImpl.getContext())
        if (bitmap == null) {
            message = "Загрузить jpg-файл не получилось"
            emitter.onError(IllegalStateException("Загрузить jpg-файл не получилось"))
            return@create
        }
        message = "Загружена картинка: $selectedFile"
        emitter.onComplete()
    }

    /** Получение bitmap картинки по uri-ссылке */
    private fun getBitmapFromUri(uri: String, context: Context): Bitmap? {
        val parcelFileDescriptor: ParcelFileDescriptor? =
            context.contentResolver.openFileDescriptor(Uri.parse(uri), "r")
        parcelFileDescriptor?.let {
            val fileDescriptor = it.fileDescriptor
            val image = BitmapFactory.decodeFileDescriptor(fileDescriptor)
            it.close()
            // Сохранение загруженной картинки в png-файл
            saveImageToPNGFile(image)
            return image
        }
        return null
    }

    /** Сохранение загруженной jpg-картики в png-файл */ //region
    fun saveImageToPNGFile(bitmap: Bitmap) {
        bitmap?.let {
            /** Сохранение картики */
            val disposable = saveImageToPNGFileCompletable()
                .doOnComplete {}
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    viewState.showToastLogMessage(message)
                }, {
                    viewState.showToastLogMessage(
                        "Ошибка сохранения png-файла: ${it.message}")
                })
            compositeDisposable.add(disposable)
        }
    }

    fun saveImageToPNGFileCompletable(): Completable =  Completable.create {
            emitter ->
        if (bitmap == null) {
            message = "Не загружена картинка для сохранения в png-файл"
            emitter.onError(IllegalStateException("Не загружена картинка для сохранения в png-файл"))
            return@create
        }
        /** Сохранение загруженной картинки в png-файл */
        bitmap?.let {
            /** Сохранение картинки в png-файл */
            bitmapToFile(it, NAME_PNG_FILE)
            emitter.onComplete()
        }
    }
    /** Сохранение картинки в png-файл */
    fun bitmapToFile(bitmap: Bitmap, fileNameToSave: String): File? {
        var file: File? = null
        return try {
            // Сохранение в корневом каталоге Internal storage
            // Сохранение в Internal storage в папке приложения:
            // /storage/emulated/0/Android/data/
            // com.login.read.write.file.jpg.png.myapplication/files/Pictures/
            file = File("${resourcesProviderImpl.getContext().getExternalFilesDir(
                Environment.DIRECTORY_PICTURES)}")

            // Сохранение картинки в Internal storage:
            /** Создание директории, если она ещё не создана */
            if (!file.exists()) {
                file.mkdirs()
            }

            /** Создание файла */
            file = File(file, "${File.separator}$fileNameToSave")
            file.createNewFile()

            /** Конвертация картинки в png */
            val bos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 0, bos)
            val bitmapdata = bos.toByteArray()

            /** Бинарная запись картинки в файл */
            val fos = FileOutputStream(file)
            fos.write(bitmapdata)
            fos.flush()
            fos.close()
            message = "Картинка успешно сохранена по адресу: $file"
            file
        } catch (e: Exception) {
            message = "Ошибка при сохранении png-файла: ${e.message}"
            file
        }
    }
}