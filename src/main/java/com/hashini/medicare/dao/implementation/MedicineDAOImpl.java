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
                "FROM medicines m " +
                "INNER JOIN medicinetypes mt on mt.medicinetype_id = m.medicinetype_id " +
                "WHERE m.city_id = ? AND ((? AND m.minimum_units >= m.units) OR ?)" +
                "ORDER BY m.medicine_id DESC";
        return jdbcTemplate.query(sql, new MedicineMapper(), cityId, lowInventory, !lowInventory);
    }

    @Override
    public List<MedicineDTO> selectMedicinesByNameAndLowInventory(String medicineName,
                                                                  Boolean lowInventory,
                                                                  int cityId) {
        String sql = "SELECT *" +
                "FROM medicines m " +
                "INNER JOIN medicinetypes mt on m.medicinetype_id = mt.medicinetype_id " +
                "WHERE m.city_id = ? AND LOWER(m.medicine_name) LIKE '%" + medicineName.toLowerCase() + "%'" +
                "AND ((? AND m.minimum_units >= m.units) OR ?)" +
                "ORDER BY m.medicine_id DESC";
        return jdbcTemplate.query(sql, new MedicineMapper(), cityId, lowInventory, !lowInventory);
    }

    @Override
    public int addMedicine(Medicine medicine,
                           int cityId) {
        String sql = "INSERT INTO medicines(medicine_name,unit_price,units,minimum_units,medicinetype_id,city_id) " +
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
        String sql = "UPDATE medicines SET medicine_name = ?, unit_price = ?, units = ?, minimum_units = ?, " +
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
                "FROM medicines m " +
                "INNER JOIN medicinetypes mt on m.medicinetype_id = mt.medicinetype_id " +
                "WHERE m.medicine_id = ? AND m.city_id = ?";
        return jdbcTemplate.query(sql, new MedicineMapper(), id, cityId).stream().findFirst();
    }

    @Override
    public Optional<MedicineDTO> selectMedicineByName(String name) {
        String sql = "SELECT *" +
                "FROM medicines m " +
                "INNER JOIN medicinetypes mt on m.medicinetype_id = mt.medicinetype_id " +
                "WHERE m.medicine_name = ?";
        return jdbcTemplate.query(sql, new MedicineMapper(), name).stream().findFirst();
    }

    @Override
    public int deleteMedicine(long id) {
        String sql = "DELETE FROM medicines WHERE medicine_id = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public void updateUnits(long id, float quantityDifference) {
        String sql = "UPDATE medicines SET units = units + ? WHERE medicine_id = ?";
        jdbcTemplate.update(sql, quantityDifference, id);
    }
}
