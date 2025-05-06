import java.util.ArrayList;
import java.util.List;

// Observer Pattern - Interface for all sensor observers
interface SensorObserver {
    void update(Sensor sensor); // Called when sensor data changes significantly
}

// Concrete Observer - Citizen who receives alerts from sensors
class Citizen implements SensorObserver {
    private String name;
    private List<Sensor> subscribedSensors = new ArrayList<>();
    
    public Citizen(String name) {
        this.name = name;
    }

    // Subscribes to a sensor and registers as an observer
    public void subscribe(Sensor sensor) {
        subscribedSensors.add(sensor);
        sensor.registerObserver(this);
    }

    // Unsubscribes from a sensor and removes itself from observer list
    public void unsubscribe(Sensor sensor) {
        subscribedSensors.remove(sensor);
        sensor.removeObserver(this);
    }

    // Called by the sensor when an alert condition is met
    @Override
    public void update(Sensor sensor) {
        System.out.println("NOTIFICATION to " + name + ":");
        System.out.println("Sensor ID: " + sensor.getId());
        String type = sensor.getType();

        // Display alert messages based on sensor type and condition

        if (type.equals("TEMPERATURE")) {
            System.out.println("Temperature Alert: " + sensor.getCurrentReading() + "°C (Below 0°C)");
        } else if (type.equals("POLLUTION")) {
            System.out.println("Pollution Alert: AQI " + sensor.getCurrentReading() + " (Above 100)");
        } else if (type.equals("NOISE")) {
            System.out.println("Noise Alert: " + sensor.getCurrentReading() + "dB (Above 85dB)");
        } else if (type.equals("CONGESTION")) {
            System.out.println("Traffic Alert: " + sensor.getCurrentReading() + "km/hr (Below 10km/hr)");
        }
        System.out.println("****************************************");
    }
} 