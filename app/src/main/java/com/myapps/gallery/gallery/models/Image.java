
package com.myapps.gallery.gallery.models;

import android.os.Parcelable;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;
import java.util.List;

@IgnoreExtraProperties
public class Image {

    private String url;

    private String name;

    public Image(String url, String name) {
        this.url = url;
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static List<Image> getMarvelImages() {
        List<Image> imageList = new ArrayList<Image>();
        imageList.add(new Image("https://firebasestorage.googleapis.com/v0/b/gallery-88af3.appspot.com/o/Gallery%2FMarvel%2FCaptain_America_1.png?alt=media&token=e1b02483-9b06-47ac-aa85-ced12d4b173f", "Captain"));
        imageList.add(new Image("https://firebasestorage.googleapis.com/v0/b/gallery-88af3.appspot.com/o/Gallery%2FMarvel%2Ff_hulk_avengersmovie.png?alt=media&token=205fd8a1-f800-4474-8680-67686d829d98", "Hulk"));
        imageList.add(new Image("https://firebasestorage.googleapis.com/v0/b/gallery-88af3.appspot.com/o/Gallery%2FMarvel%2Ffc48d0_e3fd5b7899724d94893da69948d535cf-mv2.png?alt=media&token=3f4714c4-b593-432e-95e2-4c3b8c65c45c", "IronMan"));
        return imageList;
    }
    public static List<Image> getDcImages() {
        List<Image> imageList = new ArrayList<Image>();
        imageList.add(new Image("https://firebasestorage.googleapis.com/v0/b/gallery-88af3.appspot.com/o/Gallery%2FDC%2Fc38102ff0dec3e4b55af8043c72610d8.png?alt=media&token=ba2b8ff0-deb9-44cc-aec5-71dd402ab626", "SuperMan"));
        imageList.add(new Image("https://firebasestorage.googleapis.com/v0/b/gallery-88af3.appspot.com/o/Gallery%2FDC%2FDc-universe-rebirth-geoff-johns-nova-fase-08.png?alt=media&token=f5c29fa8-7cb6-4c2c-a513-ca30590d41b3", "BatMan"));
        imageList.add(new Image("https://firebasestorage.googleapis.com/v0/b/gallery-88af3.appspot.com/o/Gallery%2FDC%2FFlash-full_0_0.png?alt=media&token=e65b30e4-374a-4c57-b7df-705d94903582", "Flash"));
        return imageList;
    }

}
