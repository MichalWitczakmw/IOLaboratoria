package vod.repository;

import vod.model.Employee;
import vod.model.Manga;
import vod.model.MangaShop;

import java.util.List;

public interface MangaDao {

    List<Manga> findAll();

    Manga findById(int id);

    List<Manga> findByEmployee(Employee employee);

    List<Manga> findByMangaShop(MangaShop mangaShop);

    Manga add(Manga manga);

}
