package com.rina.db;

import com.rina.model.DepartInfoModel;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Владислав on 29.12.2014.
 */
public class Worker {

    protected Connection connection = Connector.getConnection();

    public ModelMap getDepartmentInfo(ModelMap modelMap, String department) {
        List<DepartInfoModel>  list = new ArrayList<DepartInfoModel>();
        List<String> departments = new ArrayList<String>();

        if (department == null) {
            try {
                ResultSet rs = connection.createStatement().executeQuery("SELECT DISTINCT DEPARTMENT from CWORK");
                while (rs.next()) {
                    departments.add(rs.getString("department"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            modelMap.addAttribute("listDepartments", departments);
return modelMap;
        }


        try {
            PreparedStatement statement = connection.prepareStatement("\n" +
                    "SELECT Rank,Degree, post, name, Department, Place,Institute\n" +
                    "FROM CWork\n" +
                    "  JOIN Person ON CWork.Personid = Person.Personid JOIN LWork on LWork.Personid = Person.Personid WHERE Department = ? ;");
            statement.setString(1, department);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                DepartInfoModel departInfoModel = new DepartInfoModel();
                departInfoModel.setDegree(rs.getInt("degree"));
                departInfoModel.setDepartment(rs.getString("department"));
                departInfoModel.setInstitute(rs.getString("institute"));
                departInfoModel.setPlace(rs.getString("place"));
                departInfoModel.setPost(rs.getString("post"));
                departInfoModel.setRank(rs.getInt("rank"));
                list.add(departInfoModel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        modelMap.addAttribute("departmentInfo", list);
        return modelMap;
    }
}
