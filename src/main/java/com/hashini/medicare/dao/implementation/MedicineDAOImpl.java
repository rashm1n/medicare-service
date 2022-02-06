package com.hashini.medicare.dao.implementation;

import com.hashini.medicare.dao.MedicineDAO;
import com.hashini.medicare.dto.MedicineDTO;
import com.hashini.medicare.mapper.MedicineMapper;
import com.hashini.medicare.model.Medicine;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MedicineDAOImpl implements MedicineDAO {

    private final JdbcTemplate jdbcTemplate;

    public MedicineDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<MedicineDTO> selectMedicines() {
        String sql = "SELECT medicine.id AS id, " +
                "medicine.name," +
                "medicine.unit_price," +
                "medicine.units," +
                "m.name AS type " +
                "FROM medicine " +
                "INNER JOIN medicinetype m on m.id = medicine.medicinetype_id";
        return jdbcTemplate.query(sql, new MedicineMapper());
    }

    @Override
    public List<MedicineDTO> selectMedicinesByName(String medicineName) {
        String sql = "SELECT medicine.id AS id, " +
                "medicine.name," +
                "medicine.unit_price," +
                "medicine.units," +
                "m.name AS type " +
                "FROM medicine " +
                "INNER JOIN medicinetype m on m.id = medicine.medicinetype_id " +
                "WHERE LOWER(medicine.name) LIKE '%" + medicineName.toLowerCase() + "%'";
        return jdbcTemplate.query(sql, new MedicineMapper());
    }

    @Override
    public int addMedicine(Medicine medicine) {
        String sql = "INSERT INTO medicine(name,unit_price,units,medicinetype_id) VALUES (?,?,?,?)";
        return jdbcTemplate.update(sql,
                medicine.getName(),
                medicine.getUnitPrice(),
                medicine.getUnits(),
                medicine.getMedicineTypeId());
    }

    @Override
    public int updateMedicine(Medicine medicine, long id) {
        String sql = "UPDATE medicine SET name = ?, unit_price = ?, units = ?, medicinetype_id= ? WHERE id = ?";
        return jdbcTemplate.update(sql,
                medicine.getName(),
                medicine.getUnitPrice(),
                medicine.getUnits(),
                medicine.getMedicineTypeId(),
                id);
    }

    @Override
    public Optional<MedicineDTO> selectMedicineById(long id) {
        String sql = "SELECT medicine.id AS id, " +
                "medicine.name," +
                "medicine.unit_price," +
                "medicine.units," +
                "m.name AS type " +
                "FROM medicine " +
                "INNER JOIN medicinetype m on m.id = medicine.medicinetype_id " +
                "WHERE medicine.id = ?";
        return jdbcTemplate.query(sql, new MedicineMapper(), id).stream().findFirst();
    }

    @Override
    public Optional<MedicineDTO> selectMedicineByName(String name) {
        String sql = "SELECT medicine.id AS id, " +
                "medicine.name," +
                "medicine.unit_price," +
                "medicine.units," +
                "m.name AS type " +
                "FROM medicine " +
                "INNER JOIN medicinetype m on m.id = medicine.medicinetype_id " +
                "WHERE medicine.name = ?";
        return jdbcTemplate.query(sql, new MedicineMapper(), name).stream().findFirst();
    }

    @Override
    public int deleteMedicine(long id) {
        String sql = "DELETE FROM medicine WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
