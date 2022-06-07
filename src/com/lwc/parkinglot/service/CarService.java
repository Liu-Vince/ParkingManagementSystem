package com.lwc.parkinglot.service;

import com.lwc.parkinglot.entity.Car;

import java.util.Date;
import java.util.LinkedList;


/**
 * @author Liu Wenchang
 */
public class CarService {
    LinkedList<Car> cars = new LinkedList<>();

    public CarService() {
        cars.add(new Car(1, "京A88888", 1, true, new Date(), 0, true));
//        List<Car> cars = new LinkedList<>();
    }

    public void selectAllCar() {
        cars.forEach(System.out::println);
    }

    public boolean deleteCar(String carNumber) {
        for (Car car : cars) {
            if (car.getCarNumber().equals(carNumber)) {
                cars.remove(car);
                return true;
            }
        }
        return false;
    }

    public Car selectCarbyId(int id) {
        for (Car car : cars) {
            if (car.getId() == id) {
                return car;
            }
        }
        return null;
    }

    public Car selectCarbyCarNumber(String carNumber) {
        for (Car car : cars) {
            if (car.getCarNumber().equals(carNumber)) {
                return car;
            }
        }
        return null;
    }

    public LinkedList<Car> selectCarsByEntryTime(Date startTime, Date endingTime) {
        LinkedList<Car> cars1 = new LinkedList<>();
        for (Car car : cars) {
            if (car.getEntryTime().after(startTime) && car.getEntryTime().before(endingTime)) {
                cars1.add(car);
            }
        }
        return cars1;
    }

    public void parkingCar(String carNumber, int type, boolean monthlySubscription) {
        cars.add(new Car(cars.getLast().getId() + 1, carNumber, type, monthlySubscription, new Date(), 1, true));
    }

    public double selectPrice(String carNumber){
        double price = 0;
        for (Car car : cars) {
            if (car.getCarNumber().equals(carNumber)) {
                Date date = new Date();
                if (!car.isPresent()){
                    date = car.getLeaveTime();
                }
                if (car.isMonthlySubscription()) {
                    if (car.getType() == 1) {
                        return 480;
                    } else if (car.getType() == 2) {
                        return 960;
                    } else if (car.getType() == 3) {
                        return 360;
                    } else {
                        return -1;
                    }
                }

                long time = date.getTime() - car.getEntryTime().getTime();
                if (car.getType() == 1) {
                    if (time / (1000 * 60) < 30) {
                        return 0;
                    } else if (time / (1000 * 60 * 60) < 3) {
                        return 5;
                    } else {
                        price += 5;
                        price += ((time - (1000 * 60 * 60 * 3)) / (1000 * 60 * 60) + 1);
                        if (price > 20) {
                            return 20;
                        } else {
                            return price;
                        }
                    }
                } else if (car.getType() == 2) {
                    if (time / (1000 * 60) < 30) {
                        return 0;
                    } else if (time / (1000 * 60 * 60) < 2) {
                        return 10;
                    } else {
                        price += 10;
                        price += ((time - (1000 * 60 * 60 * 2)) / (1000 * 60 * 60) + 1) * 3;
                        if (price > 60) {
                            return 60;
                        } else {
                            return price;
                        }
                    }
                } else if (car.getType() == 3) {
                    if (time / (1000 * 60) < 120) {
                        return 0;
                    } else if (time / (1000 * 60 * 60) < 4) {
                        return 3;
                    } else {
                        price += 3;
                        price += ((time - (1000 * 60 * 60 * 4)) / (1000 * 60 * 60) + 1);
                        if (price > 20) {
                            return 20;
                        } else {
                            return price;
                        }
                    }
                } else {
                    return -1;
                }
            }
        }
        return 0;
    }

    public double pickup(String carNumber) {
        double price = 0;
        for (Car car : cars) {
            if (car.getCarNumber().equals(carNumber)) {
                Date date = new Date();
                Car tempCar = car;
                tempCar.setLeaveTime(date);
                tempCar.setPresent(false);
                cars.remove(car);
                cars.add(tempCar);
                if (car.isMonthlySubscription()) {
                    if (car.getType() == 1) {
                        tempCar.setPrice(480);
                        return 480;
                    } else if (car.getType() == 2) {
                        tempCar.setPrice(960);
                        return 960;
                    } else if (car.getType() == 3) {
                        tempCar.setPrice(360);
                        return 360;
                    } else {
                        return -1;
                    }
                }

                long time = date.getTime() - car.getEntryTime().getTime();
                if (car.getType() == 1) {
                    if (time / (1000 * 60) < 30) {
                        return 0;
                    } else if (time / (1000 * 60 * 60) < 3) {
                        tempCar.setPrice(5);
                        return 5;
                    } else {
                        price += 5;
                        price += ((time - (1000 * 60 * 60 * 3)) / (1000 * 60 * 60) + 1);
                        if (price > 20) {
                            tempCar.setPrice(20);
                            return 20;
                        } else {
                            tempCar.setPrice(price);
                            return price;
                        }
                    }
                } else if (car.getType() == 2) {
                    if (time / (1000 * 60) < 30) {
                        return 0;
                    } else if (time / (1000 * 60 * 60) < 2) {
                        tempCar.setPrice(10);
                        return 10;
                    } else {
                        price += 10;
                        price += ((time - (1000 * 60 * 60 * 2)) / (1000 * 60 * 60) + 1) * 3;
                        if (price > 60) {
                            tempCar.setPrice(60);
                            return 60;
                        } else {
                            tempCar.setPrice(price);
                            return price;
                        }
                    }
                } else if (car.getType() == 3) {
                    if (time / (1000 * 60) < 120) {
                        return 0;
                    } else if (time / (1000 * 60 * 60) < 4) {
                        tempCar.setPrice(3);
                        return 3;
                    } else {
                        price += 3;
                        price += ((time - (1000 * 60 * 60 * 4)) / (1000 * 60 * 60) + 1);
                        if (price > 20) {
                            tempCar.setPrice(20);
                            return 20;
                        } else {
                            tempCar.setPrice(price);
                            return price;
                        }
                    }
                } else {
                    return -1;
                }
            }
        }
        return 0;
    }
}
