package com.cmkjspringboot.chemicalsystem.service.Impl;

import com.cmkjspringboot.chemicalsystem.common.MyException;
import com.cmkjspringboot.chemicalsystem.entity.Export_equipment;
import com.cmkjspringboot.chemicalsystem.repository.Export_equipmentRepository;
import com.cmkjspringboot.chemicalsystem.service.Export_equipmentService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.Predicate;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class Export_equipmentImpl implements Export_equipmentService {

    @Autowired
    private Export_equipmentRepository export_equipmentRepository;

    @Override
    public boolean batchExport_equipment_Import(String fileName, MultipartFile file) throws Exception {

        boolean notNull = false;
        List<Export_equipment> export_equipment_List1 = new ArrayList<Export_equipment>();
        // 上传文件格式校验
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
        Export_equipment export_equipment1 = new Export_equipment();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("倒数行数为：" + sheet.getLastRowNum());
        for (int r = 1; r <= sheet.getLastRowNum(); r++) {
            Row row = sheet.getRow(r);
            if (row == null) {
                continue;
            }


//            if( row.getCell(0).getCellType() !=1){
//                throw new MyException("导入失败(第"+(r+1)+"行,姓名请设为文本格式)");
//            }
//            System.out.println("这是第0列解析的数据：" + row.getCell(0).getDateCellValue());
            String export_time = df.format(row.getCell(0).getDateCellValue());

            if (export_time == null || export_time.isEmpty()) {
                throw new MyException("导入失败(第" + (r + 1) + "行,出库日期未填写)");
            }

//            row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
            String equipment_name = row.getCell(1).getStringCellValue();
            if (equipment_name == null || equipment_name.isEmpty()) {
                throw new MyException("导入失败(第" + (r + 1) + "行,设备名称未填写)");
            }
            String equipment_type = row.getCell(2).getStringCellValue();
            if (equipment_type == null || equipment_type.isEmpty()) {
                throw new MyException("导入失败(第" + (r + 1) + "行,设备类型未填写)");
            }

            if (row.getCell(3).getCellType() != 0) {
                throw new MyException("导入失败(第" + (r + 1) + "行,数量请设为数值类型)");
            }
            int equipment_number = (int) row.getCell(3).getNumericCellValue();
            if (String.valueOf(equipment_number) == null || String.valueOf(equipment_number).isEmpty()) {
                throw new MyException("导入失败(第" + (r + 1) + "行,设备数量未填写)");
            }
            String equipment_position = row.getCell(4).getStringCellValue();
            if (equipment_position == null || equipment_position.isEmpty()) {
                throw new MyException("导入失败(第" + (r + 1) + "行,设备柜未填写)");
            }
            String export_user = row.getCell(5).getStringCellValue();
            if (export_user == null || export_user.isEmpty()) {
                throw new MyException("导入失败(第" + (r + 1) + "行,出库人未填写)");
            }
            String export_approver = row.getCell(6).getStringCellValue();
            if (export_approver == null || export_approver.isEmpty()) {
                throw new MyException("导入失败(第" + (r + 1) + "审核人未填写)");
            }

            export_equipment1.setExport_time(export_time);
            export_equipment1.setEquipment_name(equipment_name);
            export_equipment1.setEquipment_type(equipment_type);
            export_equipment1.setEquipment_number(equipment_number);
            export_equipment1.setEquipment_position(equipment_position);
            export_equipment1.setExport_user(export_user);
            export_equipment1.setExport_approver(export_approver);
            export_equipment1.setExport_equipment_status("已借出");

            export_equipment_List1.add(export_equipment1);
        }
        for(Export_equipment export_equipmentRecord : export_equipment_List1)
        {
            export_equipmentRepository.save(export_equipmentRecord);
            System.out.println(" 插入 " +export_equipmentRecord+ "成功！");
        }
        return notNull;
    }

    @Override
    public List<Export_equipment> findByExport_Equip_name_and_Equip_position_and_Export_Equip_status(String equipment_name, String equipment_position, String export_equipment_status) {
        return export_equipmentRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<Predicate>();

            // 如果设备名称不为空
            if(!StringUtils.isEmpty(equipment_name))
            {
                predicates.add(criteriaBuilder.equal(root.get("equipment_name"), equipment_name));
            }

            // 如果设备柜位置不为空
            if(!StringUtils.isEmpty(equipment_position))
            {
                predicates.add(criteriaBuilder.equal(root.get("equipment_position"), equipment_position));
            }

            // 如果设备使用状态不为空
            if(!StringUtils.isEmpty(export_equipment_status))
            {
                predicates.add(criteriaBuilder.equal(root.get("export_equipment_status"), export_equipment_status));
            }

            return criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
        });
    }

    @Override
    public Page<Export_equipment> findByExport_Equip_status(String export_equipment_status, Pageable pageable) {
        return export_equipmentRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<Predicate>();

            //如果出库设备状态存在
            if(!StringUtils.isEmpty(export_equipment_status))
            {
                predicates.add(criteriaBuilder.equal(root.get("export_equipment_status"), export_equipment_status));
            }

            return criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
        }, pageable);
    }
}
