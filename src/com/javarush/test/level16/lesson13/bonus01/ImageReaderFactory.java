package com.javarush.test.level16.lesson13.bonus01;
import com.javarush.test.level16.lesson13.bonus01.common.*;
/**
 * Created by polus on 08.07.2015.
 */
public class ImageReaderFactory {
    static ImageReader getReader(ImageTypes type){
        ImageReader temp;
        if (type == ImageTypes.BMP) return new BmpReader();
        if (type == ImageTypes.JPG) return new JpgReader();
        if (type == ImageTypes.PNG) return new PngReader();
    throw new IllegalArgumentException("Неизвестный тип картинки");
    }
}
