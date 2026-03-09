package vod.service.impl;

import org.springframework.stereotype.Service;
import vod.model.Employee;
import vod.model.Manga;
import vod.model.MangaShop;
import vod.repository.MangaDao;
import vod.repository.MangaShopDao;
import vod.repository.EmployeeDao;
import vod.service.MangaService;

import java.util.List;
import java.util.logging.Logger;

@Service
public class MangaServiceBean implements MangaService {

    private static final Logger log = Logger.getLogger(MangaService.class.getName());

    private EmployeeDao employeeDao;
    private MangaShopDao mangaShopDao;
    private MangaDao mangaDao;

    public MangaServiceBean(EmployeeDao employeeDao, MangaShopDao mangaShopDao, MangaDao mangaDao) {
        this.employeeDao = employeeDao;
        this.mangaShopDao = mangaShopDao;
        this.mangaDao = mangaDao;
    }

    public List<Manga> getAllMangas() {
        log.info("searching all mangas...");
        return mangaDao.findAll();
    }

    public List<Manga> getMangasByEmployee(Employee employee) {
        log.info("searching mangas by employee " + employee.getId());
        return mangaDao.findByEmployee(employee);
    }

    public List<Manga> getMangasInMangaShop(MangaShop mangaShop) {
        log.info("searching mangas in manga shop " + mangaShop.getId());
        return mangaDao.findByMangaShop(mangaShop);
    }

    public Manga getMangaById(int id) {
        log.info("searching manga by id " + id);
        return mangaDao.findById(id);
    }

    public List<MangaShop> getAllMangaShops() {
        log.info("searching all manga shops");
        return mangaShopDao.findAll();
    }

    public List<MangaShop> getMangaShopsByManga(Manga manga) {
        log.info("searching manga shops by manga " + manga.getId());
        return mangaShopDao.findByManga(manga);
    }

    public MangaShop getMangaShopById(int id) {
        log.info("searching manga shop by id " + id);
        return mangaShopDao.findById(id);
    }

    public List<Employee> getAllEmployees() {
        log.info("searching all employees");
        return employeeDao.findAll();
    }

    public Employee getEmployeeById(int id) {
        log.info("searching employee by id " + id);
        return employeeDao.findById(id);
    }

    @Override
    public Manga addManga(Manga manga) {
        log.info("about to add manga " + manga);
        return mangaDao.add(manga);
    }

    @Override
    public Employee addEmployee(Employee employee) {
        log.info("about to add employee " + employee);
        return employeeDao.add(employee);
    }

}
