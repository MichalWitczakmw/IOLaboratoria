package vod.repository;

import vod.model.MangaShop;
import vod.model.Manga;

import java.util.List;

public interface MangaShopDao {

    List<MangaShop> findAll();

    MangaShop findById(int id);

    List<MangaShop> findByManga(Manga manga);

    MangaShop save(MangaShop mangaShop);

}
