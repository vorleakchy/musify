package dev.cstv.musify.service.implementation;

import dev.cstv.musify.aop.ServiceValidation;
import dev.cstv.musify.dao.GenreDao;
import dev.cstv.musify.domain.Genre;
import dev.cstv.musify.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class GenreServiceImpl implements GenreService {

    @Autowired
    private GenreDao genreDao;

    @ServiceValidation
    @Override
    public void save(Genre genre) {

        genreDao.save(genre);
    }

    @Override
    public List<Genre> findAll() {
        return genreDao.findAll();
    }

    @ServiceValidation
    @Override
    public Genre update(Genre genre) {
        return genreDao.update(genre);
    }

    @Override
    public Genre findOne(long id) {
        return genreDao.findOne(id);
    }
}
