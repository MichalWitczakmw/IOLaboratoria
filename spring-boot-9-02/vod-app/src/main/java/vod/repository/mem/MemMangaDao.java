package vod.repository.mem;

import org.springframework.stereotype.Repository;
import vod.model.Employee;
import vod.model.Manga;
import vod.repository.MangaDao;
import vod.model.MangaShop;

import java.util.List;
import java.util.stream.Collectors;

@Repository("mangaDao")
public class MemMangaDao implements MangaDao {
    @Override
    public List<Manga> findAll() {
        return SampleData.mangas;
    }

    @Override
    public Manga findById(int id) {
        return SampleData.mangas.stream().filter(m -> m.getId() == id).findFirst().orElse(null);
    }

    @Override
    public List<Manga> findByEmployee(Employee employee) {
        return SampleData.mangas.stream()
                .filter(m -> m.getEmployee() == employee)
                .collect(Collectors.toList());
    }

    @Override
    public List<Manga> findByMangaShop(MangaShop mangaShop) {
        return SampleData.mangas.stream()
                .filter(m -> m.getMangaShops().contains(mangaShop))
                .collect(Collectors.toList());
    }

    @Override
    public Manga add(Manga manga) {
        int max = SampleData.mangas.stream()
                .max((m1, m2) -> m1.getId() - m2.getId())
                .get()
                .getId();
        manga.setId(++max);
        SampleData.mangas.add(manga);
        return manga;
    }
}
