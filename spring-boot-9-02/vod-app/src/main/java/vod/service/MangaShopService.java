package vod.service;

import vod.model.Manga;
import vod.model.MangaShop;

import java.util.List;

public interface MangaShopService {

    MangaShop getMangaShopById(int id);

    List<MangaShop> getAllMangaShops();

    List<MangaShop> getMangaShopsByManga(Manga manga);

    List<Manga> getMangasInMangaShop(MangaShop mangaShop);

    MangaShop addMangaShop(MangaShop mangaShop);

}
