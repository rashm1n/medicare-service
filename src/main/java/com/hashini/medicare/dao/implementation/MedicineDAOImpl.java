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
    public List<MedicineDTO> selectMedicinesByLowInventory(Boolean lowInventory,
                                                           int cityId) {
        String sql = "SELECT *" +
                "FROM medicine " +
                "INNER JOIN medicinetype m on m.medicinetype_id = medicine.medicinetype_id " +
                "WHERE medicine.city_id = ? AND ((? AND medicine.minimum_units >= medicine.units) OR ?)" +
                "ORDER BY medicine_id DESC";
        return jdbcTemplate.query(sql, new MedicineMapper(), cityId, lowInventory, !lowInventory);
    }

    @Override
    public List<MedicineDTO> selectMedicinesByNameAndLowInventory(String medicineName,
                                                                  Boolean lowInventory,
                                                                  int cityId) {
        String sql = "SELECT *" +
                "FROM medicine " +
                "INNER JOIN medicinetype m on m.medicinetype_id = medicine.medicinetype_id " +
                "WHERE medicine.city_id = ? AND LOWER(medicine.medicine_name) LIKE '%" + medicineName.toLowerCase() + "%'" +
                "AND ((? AND medicine.minimum_units >= medicine.units) OR ?)" +
                "ORDER BY medicine_id DESC";
        return jdbcTemplate.query(sql, new MedicineMapper(), cityId, lowInventory, !lowInventory);
    }

    @Override
    public int addMedicine(Medicine medicine,
                           int cityId) {
        String sql = "INSERT INTO medicine(medicine_name,unit_price,units,minimum_units,medicinetype_id,city_id) " +
                "VALUES (?,?,?,?,?,?)";
        return jdbcTemplate.update(sql,
                medicine.getName(),
                medicine.getUnitPrice(),
                medicine.getUnits(),
                medicine.getMinimumUnits(),
                medicine.getMedicineTypeId(),
                cityId);
    }

    @Override
    public int updateMedicine(Medicine medicine, long id) {
        String sql = "UPDATE medicine SET medicine_name = ?, unit_price = ?, units = ?, minimum_units = ?, " +
                "medicinetype_id = ? WHERE medicine_id = ?";
        return jdbcTemplate.update(sql,
                medicine.getName(),
                medicine.getUnitPrice(),
                medicine.getUnits(),
                medicine.getMinimumUnits(),
                medicine.getMedicineTypeId(),
                id);
    }

    @Override
    public Optional<MedicineDTO> selectMedicineById(long id,
                                                    int cityId) {
        String sql = "SELECT *" +
                "FROM medicine " +
                "INNER JOIN medicinetype m on m.medicinetype_id = medicine.medicinetype_id " +
                "WHERE medicine.medicine_id = ? AND medicine.city_id = ?";
        return jdbcTemplate.query(sql, new MedicineMapper(), id, cityId).stream().findFirst();
    }

    @Override
    public Optional<MedicineDTO> selectMedicineByName(String name) {
        String sql = "SELECT *" +
                "FROM medicine " +
                "INNER JOIN medicinetype m on m.medicinetype_id = medicine.medicinetype_id " +
                "WHERE medicine.medicine_name = ?";
        return jdbcTemplate.query(sql, new MedicineMapper(), name).stream().findFirst();
    }

    @Override
    public int deleteMedicine(long id) {
        String sql = "DELETE FROM medicine WHERE medicine_id = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public int updateUnits(long id, int decrementQuantity) {
        String sql = "UPDATE medicine SET units = units - ? WHERE medicine_id = ?";
        return jdbcTemplate.update(sql, decrementQuantity, id);
    }
}
