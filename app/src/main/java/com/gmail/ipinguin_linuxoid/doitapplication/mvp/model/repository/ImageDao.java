package com.gmail.ipinguin_linuxoid.doitapplication.mvp.model.repository;

import com.gmail.ipinguin_linuxoid.doitapplication.mvp.model.service.jsonObjects.getImagesJson.Images;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.List;

/**
 * менеджер по работе с сущностью картинки
 */

public class ImageDao extends BaseDaoImpl<ImageEntity, Integer> {


    public ImageDao(ConnectionSource connectionSource, Class<ImageEntity> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public List<ImageEntity> getAllImages() throws SQLException{
        return this.queryForAll();
    }



    /*
     пересохраняем картинки
     */
    public void reSaveImages(Images[] images) throws SQLException{

        TableUtils.clearTable(getConnectionSource(), ImageEntity.class);

        for (Images image: images) {
            storeImage(image);
        }
    }



    /*
    сохраняем картинки с сервачеллы
    */
    private void storeImage(Images image) throws SQLException {

        ImageEntity entity = new ImageEntity();
        entity.setImage_url(image.getSmallImagePath());
        entity.setDescription(image.getDescription());
        entity.setHashtag(image.getHashtag());
        entity.setLatitude(image.getParameters().getLatitude());
        entity.setLongitude(image.getParameters().getLongitude());
        entity.setWeather(image.getParameters().getWeather());
        entity.setAddress(image.getParameters().getAddress() == null ? "unknown": image.getParameters().getAddress());
        create(entity);
    }

}

