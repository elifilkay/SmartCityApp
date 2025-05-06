//Elif İlkay Özkan
//Yakup Atıcı
//Ömer Gökberk Gök
//Hami Deniz Kaynak
//Smart City Application


import java.util.ArrayList;
import java.util.List;

//Target interface for all sensors
interface Sensor {
    String getId(); // Returns the sensor ID
    String getType(); // Returns the type of sensor (e.g., POLLUTION, TEMPERATURE)
    boolean isMalfunctioning(); // Returns true if the sensor is malfunctioning
    void reset(); // Resets the sensor to its default state
    double getCurrentReading(); //Returns the current sensor reading
    void updateReading(double value); // Updates the sensor with a new reading
    void setMalfunctioning(boolean status); // Sets the malfunctioning status
    void registerObserver(SensorObserver observer); // Registers an observer
    void removeObserver(SensorObserver observer); // Removes an observer
    void notifyObservers(); // Notifies all registered observers
}

// Pollution sensor class (adaptee)
class PollutionSensor {
    private String id;
    private double aqiValue; // Air Quality Index value
    private boolean malfunctioning;

    public PollutionSensor(String id) {
        this.id = id;
        this.aqiValue = 50.0;
        this.malfunctioning = false;
    }
    public String getSensorId() {
        return id;
    }
    public double getAQI() {
        return aqiValue;
    }
    public void setAQI(double aqiValue) {
        this.aqiValue = aqiValue;
    }
    public boolean checkStatus() { // Returns true if working
        return !malfunctioning;
    }
    public void resetSensor() { // Reset to default AQI value
        malfunctioning = false;
        aqiValue = 50.0;
    }
    public void setMalfunctioningStatus(boolean status) {
        this.malfunctioning = status;
    }
}

// Adapter for PollutionSensor implementing Sensor interface
class PollutionSensorAdapter implements Sensor {
    private PollutionSensor pollutionSensor;
    private List<SensorObserver> observers = new ArrayList<>();

    public PollutionSensorAdapter(PollutionSensor pollutionSensor) {
        this.pollutionSensor = pollutionSensor;
    }

    @Override
    public String getId() {
        return pollutionSensor.getSensorId();
    }

    @Override
    public String getType() {
        return "POLLUTION";
    }

    @Override
    public boolean isMalfunctioning() {
        return !pollutionSensor.checkStatus();
    }

    @Override
    public void reset() {
        pollutionSensor.resetSensor();
    }

    @Override
    public double getCurrentReading() {
        return pollutionSensor.getAQI();
    }

    @Override
    public void updateReading(double value) {
        pollutionSensor.setAQI(value);
        if (value > 100) {       // Notify observers if AQI is higher than 100
            notifyObservers();
        }
    }

    @Override
    public void setMalfunctioning(boolean status) {
        pollutionSensor.setMalfunctioningStatus(status);
    }

    @Override
    public void registerObserver(SensorObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(SensorObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (SensorObserver observer : observers) {
            observer.update(this);
        }
    }
}
// Temperature sensor class
class TemperatureSensor {
    private String id;
    private double temperatureCelsius;
    private boolean malfunctioning;

    public TemperatureSensor(String id) {
        this.id = id;
        this.temperatureCelsius = 20.0; //Default temperature
        this.malfunctioning = false;
    }

    public String getSensorId() {
        return id;
    }
    public double getTemperature() {
        return temperatureCelsius;
    }
    public void setTemperature(double temperatureCelsius) {
        this.temperatureCelsius = temperatureCelsius;
    }
    public boolean checkStatus() {
        return !malfunctioning;
    }
    public void resetSensor() {
        malfunctioning = false; 
        temperatureCelsius = 20.0;
    }
    public void setMalfunctioningStatus(boolean status) {
        this.malfunctioning = status;
    }
}
// Adapter for TemperatureSensor
class TemperatureSensorAdapter implements Sensor {
    private TemperatureSensor temperatureSensor;
    private List<SensorObserver> observers = new ArrayList<>();

    public TemperatureSensorAdapter(TemperatureSensor temperatureSensor) {
        this.temperatureSensor = temperatureSensor;
    }

    @Override 
    public String getId() {
        return temperatureSensor.getSensorId(); 
    }
    
    @Override 
    public String getType() {
        return "TEMPERATURE"; 
    }
    
    @Override 
    public boolean isMalfunctioning() {
        return !temperatureSensor.checkStatus();
    }
    
    @Override 
    public void reset() {
        temperatureSensor.resetSensor();
    }
    
    @Override 
    public double getCurrentReading() {
        return temperatureSensor.getTemperature();
    }
    
    @Override 
    public void updateReading(double value) {
        temperatureSensor.setTemperature(value);
        if (value < 0) {       // Notify if temperature is below 0°C
            notifyObservers();
        }
    }
    
    @Override 
    public void setMalfunctioning(boolean status) {
        temperatureSensor.setMalfunctioningStatus(status);
    }
    
    @Override 
    public void registerObserver(SensorObserver observer) {
        observers.add(observer);
    }
    
    @Override 
    public void removeObserver(SensorObserver observer) {
        observers.remove(observer);
    }
    
    @Override 
    public void notifyObservers() {
        for (SensorObserver observer : observers) {
            observer.update(this);
        }
    }
}
// Congestion sensor class
class CongestionSensor {
    private String id;
    private double speedKmPerHour; // Vehicle speed in km/h
    private boolean malfunctioning;

    public CongestionSensor(String id) {
        this.id = id;
        this.speedKmPerHour = 40.0;
        this.malfunctioning = false;
    }

    public String getSensorId() {
        return id;
    }
    public double getVehicleSpeed() {
        return speedKmPerHour;
    }
    public void setVehicleSpeed(double speedKmPerHour) {
        this.speedKmPerHour = speedKmPerHour;
    }
    public boolean checkStatus() {
        return !malfunctioning;
    }
    public void resetSensor() {
        malfunctioning = false; 
        speedKmPerHour = 40.0;
    }
    public void setMalfunctioningStatus(boolean status) {
        this.malfunctioning = status;
    }
}
// Adapter for CongestionSensor
class CongestionSensorAdapter implements Sensor {
    private CongestionSensor congestionSensor;
    private List<SensorObserver> observers = new ArrayList<>();

    public CongestionSensorAdapter(CongestionSensor congestionSensor) {
        this.congestionSensor = congestionSensor;
    }

    @Override 
    public String getId() {
        return congestionSensor.getSensorId(); 
    }
    
    @Override 
    public String getType() {
        return "CONGESTION"; 
    }
    
    @Override 
    public boolean isMalfunctioning() {
        return !congestionSensor.checkStatus(); 
    }
    
    @Override 
    public void reset() {
        congestionSensor.resetSensor(); 
    }
    
    @Override 
    public double getCurrentReading() {
        return congestionSensor.getVehicleSpeed(); 
    }
    
    @Override 
    public void updateReading(double value) {
        congestionSensor.setVehicleSpeed(value);
        if (value < 10) {   // Notify if speed is below 10 km/h
            notifyObservers();
        }
    }
    
    @Override 
    public void setMalfunctioning(boolean status) {
        congestionSensor.setMalfunctioningStatus(status); 
    }
    
    @Override 
    public void registerObserver(SensorObserver observer) {
        observers.add(observer); 
    }
    
    @Override 
    public void removeObserver(SensorObserver observer) {
        observers.remove(observer); 
    }
    
    @Override 
    public void notifyObservers() {
        for (SensorObserver observer : observers) {
            observer.update(this);
        }
    }
}

class NoiseSensor {
    private String id;
    private double noiseDb;  // Noise level in decibels
    private boolean malfunctioning;

    public NoiseSensor(String id) {
        this.id = id;
        this.noiseDb = 60.0;
        this.malfunctioning = false;
    }

    public String getSensorId() { 
        return id; 
    }
    
    public double getNoiseLevel() { 
        return noiseDb; 
    }
    
    public void setNoiseLevel(double noiseDb) { 
        this.noiseDb = noiseDb; 
    }
    
    public boolean checkStatus() { 
        return !malfunctioning; 
    }
    
    public void resetSensor() { 
        malfunctioning = false; 
        noiseDb = 60.0; 
    }
    
    public void setMalfunctioningStatus(boolean status) { 
        this.malfunctioning = status; 
    }
}

class NoiseSensorAdapter implements Sensor {
    private NoiseSensor noiseSensor;
    private List<SensorObserver> observers = new ArrayList<>();

    public NoiseSensorAdapter(NoiseSensor noiseSensor) {
        this.noiseSensor = noiseSensor;
    }

    @Override 
    public String getId() { 
        return noiseSensor.getSensorId(); 
    }
    
    @Override 
    public String getType() { 
        return "NOISE"; 
    }
    
    @Override 
    public boolean isMalfunctioning() { 
        return !noiseSensor.checkStatus(); 
    }
    
    @Override 
    public void reset() { 
        noiseSensor.resetSensor(); 
    }
    
    @Override 
    public double getCurrentReading() { 
        return noiseSensor.getNoiseLevel(); 
    }
    
    @Override 
    public void updateReading(double value) {
        noiseSensor.setNoiseLevel(value);
        if (value > 85) {   // Notify if noise level is above 85 dB
            notifyObservers();
        }
    }
    
    @Override 
    public void setMalfunctioning(boolean status) { 
        noiseSensor.setMalfunctioningStatus(status); 
    }
    
    @Override 
    public void registerObserver(SensorObserver observer) { 
        observers.add(observer); 
    }
    
    @Override 
    public void removeObserver(SensorObserver observer) { 
        observers.remove(observer); 
    }
    
    @Override 
    public void notifyObservers() {
        for (SensorObserver observer : observers) {
            observer.update(this);
        }
    }
} 