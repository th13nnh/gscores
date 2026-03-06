package com.goldenowl.gscores.seeder;

import com.goldenowl.gscores.entity.Student;
import com.goldenowl.gscores.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private final StudentRepository studentRepository;

    public DatabaseSeeder(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (studentRepository.count() == 0) {
            System.out.println("Reading 1 million records... Please wait, this may take 2-3 minutes.");

            var inputStream = getClass().getResourceAsStream("/diem_thi_thpt_2024.csv");
            if (inputStream == null) return;

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                String line;
                reader.readLine(); // Skip header

                List<Student> batch = new ArrayList<>();
                int count = 0;

                while ((line = reader.readLine()) != null) {
                    String[] data = line.split(",", -1);
                    Student s = new Student();
                    s.setSbd(data[0]);
                    s.setToan(pD(data[1]));
                    s.setNguVan(pD(data[2]));
                    s.setNgoaiNgu(pD(data[3]));
                    s.setVatLi(pD(data[4]));
                    s.setHoaHoc(pD(data[5]));
                    s.setSinhHoc(pD(data[6]));
                    s.setLichSu(pD(data[7]));
                    s.setDiaLi(pD(data[8]));
                    s.setGdcd(pD(data[9]));
                    if (data.length > 10) s.setMaNgoaiNgu(data[10]);

                    batch.add(s);
                    count++;

                    // Save every 1000 records to keep memory usage low
                    if (count % 1000 == 0) {
                        studentRepository.saveAll(batch);
                        batch.clear();
                        if (count % 50000 == 0) System.out.println("Processed " + count + " rows...");
                    }
                }
                // Save remaining
                if (!batch.isEmpty()) studentRepository.saveAll(batch);
                System.out.println("Finished! Total records: " + count);
            }
        } else {
            System.out.println("Data already exists in PostgreSQL. Skipping seeder.");
        }
    }

    private Double pD(String v) {
        return (v == null || v.isBlank()) ? null : Double.parseDouble(v);
    }
}