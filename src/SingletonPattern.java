
//Elif İlkay Özkan
//Yakup Atıcı
//Ömer Gökberk Gök
//Hami Deniz Kaynak
//Smart City Application

import java.util.ArrayList;
import java.util.List;

// Singleton class representing the central system for monitoring sensor data
class DataMonitoringDivision {
    private static DataMonitoringDivision instance; // Lazy instantiation
    private List<Neighborhood> cityNeighborhoods = new ArrayList<>();

    // Private constructor for Singleton pattern
    private DataMonitoringDivision() {}

    // Returns the single instance of the monitoring division
    public static DataMonitoringDivision getInstance() {
        if (instance == null) {
            instance = new DataMonitoringDivision();
        }
        return instance;
    }

    // Adds a neighborhood to be monitored
    public void addNeighborhood(Neighborhood neighborhood) {
        cityNeighborhoods.add(neighborhood);
    }

    // Uses a visitor to check all malfunctioning sensors in the city
    public int checkMalfunctioningSensors() {
        MalfunctioningSensorVisitor visitor = new MalfunctioningSensorVisitor();
        for (Neighborhood neighborhood : cityNeighborhoods) {
            neighborhood.accept(visitor);
        }
        return visitor.getMalfunctioningCount();
    }

    // Executes a command (Command Pattern)
    public void executeCommand(Command command) {
        command.execute();
    }

    public static void main(String[] args) {

        // Create city structure using Composite pattern
        Neighborhood konak = new Neighborhood("Konak");
        Neighborhood balcova = new Neighborhood("Balcova");

        Street cankayaStreet = new Street("Cankaya");
        Street sakaryaStreet = new Street("Sakarya");
        Street gultepeStreet = new Street("Gültepe");

        // Add streets to neighborhoods
        konak.addComponent(cankayaStreet);
        konak.addComponent(gultepeStreet);
        balcova.addComponent(sakaryaStreet);

        // Create apartments and poles
        Apartment apartment1 = new Apartment("Bahariye Apt 1");
        Apartment apartment2 = new Apartment("Sogutlucesme Apt 1");
        Apartment apartment3 = new Apartment("Barbaros Apt 1");

        Pole pole1 = new Pole("Bahariye Pole 1");
        Pole pole2 = new Pole("Sogutlucesme Pole 1");
        Pole pole3 = new Pole("Barbaros Pole 1");

        // Add apartments and poles to streets
        cankayaStreet.addComponent(apartment1);
        cankayaStreet.addComponent(pole1);
        sakaryaStreet.addComponent(apartment2);
        sakaryaStreet.addComponent(pole2);
        gultepeStreet.addComponent(apartment3);
        gultepeStreet.addComponent(pole3);

        // Create sensors (Adapters for different sensor types)
        PollutionSensor pollutionSensor1 = new PollutionSensor("P001");
        PollutionSensor pollutionSensor2 = new PollutionSensor("P002");
        TemperatureSensor temperatureSensor1 = new TemperatureSensor("T001");
        TemperatureSensor temperatureSensor2 = new TemperatureSensor("T002");
        CongestionSensor congestionSensor1 = new CongestionSensor("C001");
        CongestionSensor congestionSensor2 = new CongestionSensor("C002");
        NoiseSensor noiseSensor1 = new NoiseSensor("N001");
        NoiseSensor noiseSensor2 = new NoiseSensor("N002");

        // Wrap sensors with Adapters
        Sensor pollutionAdapter1 = new PollutionSensorAdapter(pollutionSensor1);
        Sensor pollutionAdapter2 = new PollutionSensorAdapter(pollutionSensor2);
        Sensor temperatureAdapter1 = new TemperatureSensorAdapter(temperatureSensor1);
        Sensor temperatureAdapter2 = new TemperatureSensorAdapter(temperatureSensor2);
        Sensor congestionAdapter1 = new CongestionSensorAdapter(congestionSensor1);
        Sensor congestionAdapter2 = new CongestionSensorAdapter(congestionSensor2);
        Sensor noiseAdapter1 = new NoiseSensorAdapter(noiseSensor1);
        Sensor noiseAdapter2 = new NoiseSensorAdapter(noiseSensor2);

        // Add sensors to apartments and poles
        apartment1.addSensor(pollutionAdapter1);
        apartment1.addSensor(temperatureAdapter1);
        apartment2.addSensor(congestionAdapter1);
        apartment3.addSensor(noiseAdapter1);
        apartment3.addSensor(congestionAdapter2);
        pole1.addSensor(pollutionAdapter2);
        pole2.addSensor(temperatureAdapter2);
        pole3.addSensor(congestionAdapter2);
        pole3.addSensor(noiseAdapter2);
        pole2.addSensor(congestionAdapter2);

        // Create citizens and subscribe them to sensors (Observer pattern)
        Citizen citizen1 = new Citizen("Sezer");
        Citizen citizen2 = new Citizen("Fatma");

        citizen1.subscribe(pollutionAdapter1);
        citizen1.subscribe(temperatureAdapter1);
        citizen2.subscribe(congestionAdapter1);
        citizen2.subscribe(noiseAdapter1);
        citizen2.subscribe(congestionAdapter2);
        citizen1.unsubscribe(temperatureAdapter1);// Unsubscribe from temperature sensor so Sezer won't receive temperature alerts
        citizen2.subscribe(pollutionAdapter2);


        DataMonitoringDivision monitoringDivision = DataMonitoringDivision.getInstance();
        monitoringDivision.addNeighborhood(konak);
        monitoringDivision.addNeighborhood(balcova);

        // Simulate sensor readings (Triggers alerts if conditions are met)
        System.out.println("\n--- Sensor Activity Simulation ---");
        temperatureAdapter1.updateReading(-5.0);
        pollutionAdapter1.updateReading(120.0);
        congestionAdapter1.updateReading(8.0);
        noiseAdapter1.updateReading(90.0);
        congestionAdapter2.updateReading(55.0);
        pollutionAdapter2.updateReading(78.0);// This will not trigger an alert  because Fatma's AQI value  is under 100

        // Simulate malfunctioning sensors
        temperatureAdapter1.setMalfunctioning(true);//Located in Konak
        temperatureAdapter2.setMalfunctioning(true); //Located in Balçova, so it is NOT included in this reset command.
        pollutionAdapter2.setMalfunctioning(true); //Located in Konak
        noiseAdapter1.setMalfunctioning(true); // Located in Konak


        // Execute commands (Command pattern used here)
        System.out.println("\n--- Commands ---");
        Command queryCommand = new QuerySensorsCommand(monitoringDivision);
        Command resetCommand = new ResetSensorsCommand(konak);// This will reset only the malfunctioning sensors under the 'Konak' neighborhood


        monitoringDivision.executeCommand(queryCommand);// Lists all malfunctioning sensors across the entire city
        monitoringDivision.executeCommand(resetCommand);// Resets only those in Konak neighborhood

        // Check status after reset
        System.out.println("\n--- Status After Reset ---");
        monitoringDivision.executeCommand(queryCommand); // Shows remaining malfunctioning sensors
    }
} 
