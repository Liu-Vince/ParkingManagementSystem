package com.lwc.parkinglot.view;


import com.lwc.parkinglot.entity.Car;
import com.lwc.parkinglot.service.CarService;
import com.lwc.parkinglot.service.UserService;
import com.lwc.parkinglot.utils.Utility;

import java.util.Date;
import java.util.LinkedList;


/**
 * @author Liu Wenchang
 * 主界面
 */
public class PLView {
    CarService carService = new CarService();
    UserService userService = new UserService();
    //控制是否退出菜单
    private boolean loop = true;
    //接收用户的选择
    private String key = "";

    public static void main(String[] args) {
        new PLView().mainMenu();
    }


    private void adminCars() {
        System.out.println("\n===============车辆管理菜单==============");
        System.out.println("\t\t 1 列出所有车辆信息");
        System.out.println("\t\t 2 删除车辆信息");
        System.out.println("\t\t 3 查询车辆信息");
        System.out.println("\t\t 4 查询车辆需交费用");
        System.out.println("\t\t 9 退出停车系统");
        System.out.println("请输入您的选择: ");
        key = Utility.readString(1);
        switch (key) {
            case "1":
                carService.selectAllCar();
                break;
            case "2":
                System.out.print("请输入要删除车辆的车牌号: ");
                String carNumber = Utility.readString(50);
                System.out.println("\t\t 您正在删除车辆信息，输入y继续");
                String key1 = Utility.readString(1);
                if (key1.equals("y") || key1.equals("Y")) {
                    if (carService.deleteCar(carNumber)) {
                        System.out.println("删除成功");
                    } else {
                        System.out.println("不存在您要删除的车辆");
                    }
                } else {
                    System.out.println("您已经取消删除操作");
                }
                break;
            case "3":
                System.out.println("\n===============车辆查询菜单==============");
                System.out.println("\t\t 1 根据车辆ID查询");
                System.out.println("\t\t 2 根据车牌号查询");
                System.out.println("\t\t 3 根据时间查询");
                System.out.println("\t\t 9 退出停车系统");
                System.out.println("请输入您的选择: ");
                key = Utility.readString(1);
                switch (key) {
                    case "1":
                        System.out.println("请输入车辆ID: ");
                        int id = Utility.readInt();
                        System.out.println(carService.selectCarbyId(id));
                        break;
                    case "2":
                        System.out.print("请输入车牌号: ");
                        carNumber = Utility.readString(50);
                        System.out.println(carService.selectCarbyCarNumber(carNumber));
                        break;
                    case "3":
                        System.out.println("请输入开始的年份数字表示如(2001): ");
                        int year = Utility.readInt();
                        System.out.println("请输入开始的月份数字表示如(1): ");
                        int m = Utility.readInt();
                        System.out.println("请输入开始的天数字表示如(16): ");
                        int d = Utility.readInt();
                        System.out.println("请输入结束的年份数字表示如(2001): ");
                        int year1 = Utility.readInt();
                        System.out.println("请输入结束的月份数字表示如(1): ");
                        int m1 = Utility.readInt();
                        System.out.println("请输入结束的天数字表示如(16): ");
                        int d1 = Utility.readInt();
                        LinkedList<Car> cars = carService.selectCarsByEntryTime(new Date(year - 1900, m - 1, d), new Date(year1 - 1900, m1 - 1, d1));
                        cars.forEach(System.out::println);
                        break;
                    case "9":
                        loop = false;
                        break;
                    default:
                        System.out.println("您的输入有误，请重新输入: ");
                }
                break;
            case "4":
                System.out.print("请输入车牌号: ");
                carNumber = Utility.readString(50);
                double price = carService.selectPrice(carNumber);
                if (price == -1) {
                    System.out.println("车类型错误");
                } else {
                    System.out.println("此车需要缴纳停车费" + (int) price + "元");
                }
                break;
            case "9":
                loop = false;
                break;
            default:
                System.out.println("您的输入有误，请重新输入: ");
        }
    }

    private void parkingCar() {
        System.out.println("\n===============停/取车菜单==============");
        System.out.println("\t\t 1 我要停车");
        System.out.println("\t\t 2 我要取车");
        System.out.println("\t\t 9 退出停车系统");
        System.out.println("请输入您的选择: ");
        key = Utility.readString(1);
        switch (key) {
            case "1":
                System.out.print("请输入车牌号: ");
                String carNumber = Utility.readString(50);
                System.out.println("请输入车辆类型    \n" +
                        "    中小型车：1\n" +
                        "    大型车：2\n" +
                        "    新能源车：3: ");
                int type = Utility.readInt();
                System.out.print("是否包月(是请输入1，否请输入0):");
                int isMonthlySubscription = Utility.readInt();
                if (isMonthlySubscription == 1) {
                    carService.parkingCar(carNumber, type, true);
                } else {
                    carService.parkingCar(carNumber, type, false);
                }
                break;
            case "2":
                System.out.print("请输入车牌号: ");
                carNumber = Utility.readString(50);
                double price = carService.pickup(carNumber);
                if (price == -1) {
                    System.out.println("车类型错误");
                } else {
                    System.out.println("您此次需要缴纳停车费" + (int) price + "元");
                }
                break;
            case "9":
                loop = false;
                break;
            default:
                System.out.println("您的输入有误，请重新输入: ");
        }
    }


    private void updateUser() {

        String id;
        String pwd;

        System.out.println("\n===============密码管理菜单==============");
        System.out.println("\t\t 1 管理员密码修改");
        System.out.println("\t\t 2 用户密码管理");
        System.out.println("\t\t 9 退出停车系统");
        System.out.println("请输入您的选择: ");
        key = Utility.readString(1);
        switch (key) {
            case "1":
                System.out.print("请输入修改为密码: ");
                pwd = Utility.readString(50);
                userService.updateAdminUserPassword(pwd);
                System.out.println("您已经修改密码成功请返回登录~");
                break;
            case "2":
                boolean loop1 = true;
                while (loop1) {
                    System.out.print("请要修改输入用户名: ");
                    id = Utility.readString(50);
                    System.out.print("请输入密码: ");
                    pwd = Utility.readString(50);
                    if (userService.updateUserPassword(id, pwd)) {
                        loop1 = false;
                    } else {
                        System.out.println("不存在此用户");
                    }
                }
                break;
            case "9":
                loop = false;
                break;
            default:
                System.out.println("您的输入有误，请重新输入: ");
        }
    }


    //显示主菜单
    public void mainMenu() {


        while (loop) {
            System.out.println("===============云大停车场==============");
            System.out.println("\t\t 1 登录停车场管理系统");
            System.out.println("\t\t 2 注册停车场管理系统");
            System.out.println("\t\t 9 退出停车场管理系统");
            System.out.println("请输入你的选择: ");
            key = Utility.readString(1);
            String id;
            String pwd;
            switch (key) {
                case "1":
                    System.out.print("请输入用户名: ");
                    id = Utility.readString(50);
                    System.out.print("请输入密码: ");
                    pwd = Utility.readString(50);
                    if (userService.selectType(id, pwd) == 1) {
                        System.out.println("===============登录成功==============\n");
                        //显示二级菜单
                        while (loop) {
                            System.out.println("\n===============停车场管理系统二级菜单==============");
                            System.out.println("\t\t 1 密码管理");
                            System.out.println("\t\t 2 车辆管理");
                            System.out.println("\t\t 9 退出停车系统");
                            System.out.println("请输入您的选择: ");
                            key = Utility.readString(1);
                            switch (key) {
                                case "1":
                                    updateUser();
                                    break;
                                case "2":
                                    adminCars();
                                    break;
                                case "9":
                                    loop = false;
                                    break;
                                default:
                                    System.out.println("您的输入有误，请重新输入: ");
                            }
                        }
                    } else if (userService.selectType(id, pwd) == 2) {
                        System.out.println("\n===============欢迎停车==============");
                        parkingCar();
                    } else {
                        System.out.println("===============登录失败==============");
                    }
                    break;
                case "2":
                    System.out.print("请输入员工号: ");
                    id = Utility.readString(50);
                    System.out.print("请输入密码: ");
                    pwd = Utility.readString(50);
                    userService.addUser(id, pwd);
                    System.out.println("您已经注册成功请返回登录~");
                    break;
                case "9":
                    loop = false;
                    break;
                default:
                    System.out.println("您的输入有误，请重新输入: ");
            }
        }
        System.out.println("你退出了停车场管理系统~");
    }


}

