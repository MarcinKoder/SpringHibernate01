package pl.coderslab.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import pl.coderslab.domain.dao.PublisherDao;
import pl.coderslab.domain.model.Publisher;

public class PublisherConverter implements Converter<String, Publisher> {

    private PublisherDao publisherDao;

    @Autowired
    public void setPublisherDao(PublisherDao publisherDao) {
        this.publisherDao = publisherDao;
    }

    @Override
    public Publisher convert(String s) {
        return publisherDao.find(Long.parseLong(s));
    }
}
