package vod.service;

import vod.model.Employee;
import vod.model.Manga;

import java.util.List;

public interface MangaService {

    List<Manga> getAllMangas();

    List<Manga> getMangasByEmployee(Employee employee);

    Manga getMangaById(int id);

    Manga addManga(Manga manga);

    List<Employee> getAllEmployees();

    Employee getEmployeeById(int id);

    Employee addEmployee(Employee employee);
}
