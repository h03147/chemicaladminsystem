package com.cmkjspringboot.chemicalsystem.service.Impl;

import com.cmkjspringboot.chemicalsystem.common.MyException;
import com.cmkjspringboot.chemicalsystem.entity.Export_chemical_medicine;
import com.cmkjspringboot.chemicalsystem.repository.Export_chemical_medicineRepository;
import com.cmkjspringboot.chemicalsystem.service.Export_chemical_medicineService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.Predicate;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class Export_chemical_medicineServiceImpl implements Export_chemical_medicineService {

    @Autowired
    private Export_chemical_medicineRepository export_chemical_medicineRepository;


    // excel批量出库信息导入的实现类
    @Override
    public boolean batchImport(String fileName, MultipartFile file) throws Exception {

        boolean notNull = false;
        List<Export_chemical_medicine> exportchemicalmedicineList = new ArrayList<Export_chemical_medicine>();
        if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
            throw new MyException("上传文件格式不正确");
        }
        boolean isExcel2003 = true;
        if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
            isExcel2003 = false;
        }
        InputStream is = file.getInputStream();
        Workbook wb = null;
        if (isExcel2003) {
            wb = new HSSFWorkbook(is);
        } else {
            wb = new XSSFWorkbook(is);
        }
        Sheet sheet = wb.getSheetAt(0);
        if(sheet!=null){
            notNull = true;
        }
        Export_chemical_medicine export_chemical_medicine;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("倒数行数为：" + sheet.getLastRowNum());
        for (int r = 1; r <= sheet.getLastRowNum(); r++) {
            Row row = sheet.getRow(r);
            if (row == null){
                continue;
            }

            export_chemical_medicine = new Export_chemical_medicine();

//            if( row.getCell(0).getCellType() !=1){
//                throw new MyException("导入失败(第"+(r+1)+"行,姓名请设为文本格式)");
//            }
//            System.out.println("这是第0列解析的数据：" + row.getCell(0).getDateCellValue());
            String export_time = df.format(row.getCell(0).getDateCellValue());

            if(export_time == null || export_time.isEmpty()){
                throw new MyException("导入失败(第"+(r+1)+"行,出库日期未填写)");
            }

//            row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
            String medicine_name = row.getCell(1).getStringCellValue();
            if(medicine_name == null || medicine_name.isEmpty()){
                throw new MyException("导入失败(第"+(r+1)+"行,药品名称未填写)");
            }
            String medicine_type = row.getCell(2).getStringCellValue();
            if(medicine_type == null || medicine_type.isEmpty()){
                throw new MyException("导入失败(第"+(r+1)+"行,药品类型未填写)");
            }

            if( row.getCell(3).getCellType() !=0){
                throw new MyException("导入失败(第"+(r+1)+"行,数量请设为数值类型)");
            }
            int medicine_number = (int) row.getCell(3).getNumericCellValue();
            if( String.valueOf(medicine_number) == null || String.valueOf(medicine_number).isEmpty()){
                throw new MyException("导入失败(第"+(r+1)+"行,数量未填写)");
            }
            String measurement = row.getCell(4).getStringCellValue();
            if(measurement == null || measurement.isEmpty()){
                throw new MyException("导入失败(第"+(r+1)+"行,药品单位未填写)");
            }
            String medicine_box = row.getCell(5).getStringCellValue();
            if(medicine_box == null || medicine_box.isEmpty()){
                throw new MyException("导入失败(第"+(r+1)+"行,药品柜未填写)");
            }
            String expired_time = df.format(row.getCell(6).getDateCellValue());
            if(expired_time == null || expired_time.isEmpty()){
                throw new MyException("导入失败(第"+(r+1)+"行,药品过期时间未填写)");
            }
            String export_user = row.getCell(7).getStringCellValue();
            if(export_user == null || export_user.isEmpty()){
                throw new MyException("导入失败(第"+(r+1)+"行,出库人未填写)");
            }
            String export_approver = row.getCell(8).getStringCellValue();
            if(export_approver == null || export_approver.isEmpty()){
                throw new MyException("导入失败(第"+(r+1)+"审核人未填写)");
            }

//            Date date;
//            if(row.getCell(3).getCellType() !=0){
//                throw new MyException("导入失败(第"+(r+1)+"行,入职日期格式不正确或未填写)");
//            }else{
//                date = row.getCell(3).getDateCellValue();
//            }

//            String des = row.getCell(4).getStringCellValue();

            export_chemical_medicine.setExport_time(export_time);
            export_chemical_medicine.setMedicine_name(medicine_name);
            export_chemical_medicine.setMedicine_type(medicine_type);
            export_chemical_medicine.setMedicine_number(medicine_number);
            export_chemical_medicine.setMeasurement(measurement);
            export_chemical_medicine.setMedicine_box(medicine_box);
            export_chemical_medicine.setExpired_time(expired_time);
            export_chemical_medicine.setExport_user(export_user);
            export_chemical_medicine.setExport_approver(export_approver);

            exportchemicalmedicineList.add(export_chemical_medicine);
        }
        for (Export_chemical_medicine exportchemicalmedicineResord : exportchemicalmedicineList) {
//            String name = userResord.getName();
//            int cnt = userMapper.selectByName(name);
//            if (cnt == 0) {
//                userMapper.addUser(userResord);
//                System.out.println(" 插入 "+userResord);
//            } else {
//                userMapper.updateUserByName(userResord);
//                System.out.println(" 更新 "+userResord);
//            }
            export_chemical_medicineRepository.save(exportchemicalmedicineResord);
            System.out.println(" 插入 " +exportchemicalmedicineResord+ "成功！");
        }
        return notNull;

    }


    // 三条件出库查询的实现类
    @Override
    public List<Export_chemical_medicine> findByCondition(String medicine_name, String medicine_box, String expired_time) {
        return export_chemical_medicineRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<Predicate>();
//            System.out.println("多条件传来了药品名称和药品柜位置以及药品过期时间：" + medicine_name + "$$$$$" + medicine_box + "$$$$$" + expired_time);

            // 药品名称是否存在
            if(!StringUtils.isEmpty(medicine_name)) {
                predicates.add(criteriaBuilder.equal(root.get("medicine_name"), medicine_name));
            }

            // 药品柜是否存在
            if(!StringUtils.isEmpty(medicine_box)) {
                predicates.add(criteriaBuilder.equal(root.get("medicine_box"), medicine_box));
            }

            // 当前药品的过期日期是否存在
            if(!StringUtils.isEmpty(expired_time)) {
                predicates.add(criteriaBuilder.equal(root.get("expired_time"), expired_time));
            }

//            System.out.println("根据药瓶名称和药品柜以及过期时间查询的结果：" + criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction());
            return criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();

        });
    }

    @Override
    public List<Export_chemical_medicine> findByExport_time(String export_time) {
        return export_chemical_medicineRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<Predicate>();

            // 要传入的出库日期是否存在
            if(!StringUtils.isEmpty(export_time)) {
                predicates.add(criteriaBuilder.like(root.get("export_time"), export_time + "%"));
            }

            return criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
        });
    }
}
