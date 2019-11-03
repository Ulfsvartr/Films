package com.tstu.csv;

import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CSVParser {
    public static List<FilmCSV> getFilmsFromFile(String fileName) throws FileNotFoundException {
        CsvToBean csv = new CsvToBean();
        List<FilmCSV> films = new ArrayList<>();
        Path path = Paths.get("src/main/java/com/tstu", fileName);
        CSVReader csvReader = new CSVReader(new FileReader(new File(path.toUri())),';');
        //Set column mapping strategy

        List list = csv.parse(setColumMapping(), csvReader);
        for (Object object : list) {
            FilmCSV film = (FilmCSV) object;
            films.add(film);
        }
        return films;
    }
    private static ColumnPositionMappingStrategy setColumMapping()
    {
        ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
        strategy.setType(FilmCSV.class);
        String[] columns = new String[] {"name", "imdbId", "type", "genre", "releaseDate"};
        strategy.setColumnMapping(columns);
        return strategy;
    }

}
