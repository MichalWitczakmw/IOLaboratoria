package vod.repository.mem;

import org.springframework.stereotype.Repository;
import vod.model.MangaShop;
import vod.repository.MangaShopDao;
import vod.model.Manga;

import java.util.List;
import java.util.stream.Collectors;

@Repository("mangaShopDao")
public class MemMangaShopDao implements MangaShopDao {

    @Override
    public List<MangaShop> findAll() {
        return SampleData.mangaShops;
    }

    @Override
    public MangaShop findById(int id) {
        return SampleData.mangaShops.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
    }

    @Override
    public List<MangaShop> findByManga(Manga manga) {
        return SampleData.mangaShops.stream()
                .filter(ms -> ms.getMangas().contains(manga))
                .collect(Collectors.toList());
    }

    @Override
    public MangaShop save(MangaShop mangaShop) {
        int maxId = SampleData.mangaShops.stream()
                .sorted((c1, c2) -> c2.getId() - c1.getId())
                .findFirst()
                .map(c -> c.getId())
                .orElse(0);
        mangaShop.setId(maxId + 1);
        SampleData.mangaShops.add(mangaShop);
        return mangaShop;
    }

}
