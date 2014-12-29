package com.rina.db;

import com.rina.model.*;
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

    public ModelMap getWorkInfo(ModelMap modelMap, String work) {
        List<Work> listWorks = new ArrayList<Work>();
        List<String> works =new ArrayList<String>();

        if (work == null) {
            try {
                ResultSet rs = connection.createStatement().executeQuery("SELECT DISTINCT Workplace from LWork");
                while (rs.next())
                    works.add(rs.getString("workplace"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            modelMap.addAttribute("works", works);
            return modelMap;
        }

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT Workplace, Workaddress, WORPHONE, Reason FROM LWork WHERE Workplace = ?");
            statement.setString(1, work);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Work work1 = new Work();
                work1.setReason(rs.getString("reason"));
                work1.setWorkAddress(rs.getString("workaddress"));
                work1.setWorkPhone(rs.getString("worphone"));
                work1.setWorkPlace(rs.getString("workplace"));
                listWorks.add(work1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        modelMap.addAttribute("infoWorks", listWorks);
        return modelMap;

    }

    public ModelMap getInfoAboutWorkerInDepartment(ModelMap model, String department) {
        List<String> departments = new ArrayList<String>();
        List<DepartInfoWorker> departInfoWorkers = new ArrayList<DepartInfoWorker>();
        if (department == null) {
            try {
                ResultSet rs = connection.createStatement().executeQuery("SELECT DISTINCT DEPARTMENT from CWORK");
                while (rs.next()) {
                    departments.add(rs.getString("department"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            model.addAttribute("listDepartments2", departments);
            return model;
        }

        try {
            PreparedStatement prepared =connection.prepareStatement("select * from CWork JOIN Person on Person.Personid = CWork.Personid where Department = ?");
            prepared.setString(1, department);
            ResultSet rs = prepared.executeQuery();
            while (rs.next()) {
                DepartInfoWorker departInfoWorker =new DepartInfoWorker();
                departInfoWorker.setRank(rs.getInt("rank"));
                departInfoWorker.setAddress(rs.getString("address"));
                departInfoWorker.setDepartment(rs.getString("department"));
                departInfoWorker.setName(rs.getString("name"));
                departInfoWorker.setPhone(rs.getString("phone"));
                departInfoWorker.setRegion(rs.getString("region"));
                departInfoWorkers.add(departInfoWorker);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        model.addAttribute("workerInfoList", departInfoWorkers);
        return model;
    }

    public ModelMap first(ModelMap modelMap) {
        List<ModelFirst> list = new ArrayList<ModelFirst>();
        try {
            ResultSet rs = connection.createStatement().executeQuery("SELECT * from LWork JOIN Person on Person.Personid = LWork.Personid ");
            while (rs.next()) {
                ModelFirst modelFirst = new ModelFirst();
                modelFirst.setName(rs.getString("name"));
                modelFirst.setPassport("passport");
                modelFirst.setReason(rs.getString("reason"));
                modelFirst.setWorkaddress(rs.getString("workaddress"));
                modelFirst.setWorkplace(rs.getString("workplace"));
                list.add(modelFirst);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        modelMap.addAttribute("first", list);
        return modelMap;
    }

    public ModelMap last(ModelMap model) {
        List<LastQuery> lastQueries = new ArrayList<LastQuery>();

        try {
            ResultSet rs = connection.createStatement().executeQuery("select max(Year) as max, count(CWork.Personid) as count FROM CWork JOIN Education ON CWork.Personid = Education.Personid GROUP BY CWork.Personid, Education");
            while (rs.next()) {
                LastQuery lastQuery =new LastQuery();
                lastQuery.setCount(rs.getInt("count"));
                lastQuery.setMax(rs.getInt("max"));
                lastQueries.add(lastQuery);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        model.addAttribute("last", lastQueries);
        return model;

    }
}
