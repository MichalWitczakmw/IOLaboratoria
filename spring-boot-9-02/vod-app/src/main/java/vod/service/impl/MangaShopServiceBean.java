package vod.service.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import vod.model.MangaShop;
import vod.model.Manga;
import vod.repository.MangaDao;
import vod.repository.MangaShopDao;
import vod.service.MangaShopService;

import java.util.List;
import java.util.logging.Logger;

@Service
@Scope("prototype")
public class MangaShopServiceBean implements MangaShopService {

    private static final Logger log = Logger.getLogger(MangaShopService.class.getName());

    private MangaShopDao mangaShopDao;
    private MangaDao mangaDao;

    public MangaShopServiceBean(MangaShopDao mangaShopDao, MangaDao mangaDao) {
        log.info("creating manga shop service bean");
        this.mangaShopDao = mangaShopDao;
        this.mangaDao = mangaDao;
    }

    @Override
    public MangaShop getMangaShopById(int id) {
        log.info("searching manga shop by id " + id);
        return mangaShopDao.findById(id);
    }

    @Override
    public List<Manga> getMangasInMangaShop(MangaShop mangaShop) {
        log.info("searching mangas available in manga shop " + mangaShop.getId());
        return mangaDao.findByMangaShop(mangaShop);
    }

    @Override
    public List<MangaShop> getAllMangaShops() {
        log.info("searching all manga shops");
        return mangaShopDao.findAll();
    }

    @Override
    public List<MangaShop> getMangaShopsByManga(Manga manga) {
        log.info("searching manga shops by manga " + manga.getId());
        return mangaShopDao.findByManga(manga);
    }

    @Override
    public MangaShop addMangaShop(MangaShop mangaShop) {
        log.info("adding new manga shop " + mangaShop);
        return mangaShopDao.save(mangaShop);
    }

}
