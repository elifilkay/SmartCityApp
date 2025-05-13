//Elif İlkay Özkan
//Yakup Atıcı
//Ömer Gökberk Gök
//Hami Deniz Kaynak
//Smart City Application


// Visitor interface for sensors
interface SensorVisitor {
    // Method to visit a sensor
    void visit(Sensor sensor);
}

// Concrete visitor class to count malfunctioning sensors
class MalfunctioningSensorVisitor implements SensorVisitor {
    private int malfunctioningCount = 0;

    @Override
    public void visit(Sensor sensor) {
        // If the sensor is malfunctioning, increment the count and print its details
        if (sensor.isMalfunctioning()) {
            malfunctioningCount++;
            System.out.println("Found malfunctioning sensor: " + sensor.getId() + " of type " + sensor.getType());
        }
    }
    // Returns the total number of malfunctioning sensors visited
    public int getMalfunctioningCount() {
        return malfunctioningCount;
    }
}
// Concrete visitor class to reset malfunctioning sensors
class ResetSensorVisitor implements SensorVisitor {
    @Override
    public void visit(Sensor sensor) {
        // If the sensor is malfunctioning, reset it and print its details
        if (sensor.isMalfunctioning()) {
            sensor.reset();
            System.out.println("Reset sensor: " + sensor.getId() + " of type " + sensor.getType());
        }
    }
} 
