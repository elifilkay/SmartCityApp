import java.util.ArrayList;
import java.util.List;

// Component interface - defines operation for both leaf and composite objects
interface CityComponent {
    void addComponent(CityComponent component); // Adds a component
    void removeComponent(CityComponent component); // Removes a component
    CityComponent getComponent(int index); // Gets a component
    String getName(); // Returns the name of the component
    void accept(SensorVisitor visitor); // Accepts a visitor to process sensors
}

//Composite class -represents a neighborhood containing streets
class Neighborhood implements CityComponent {
    private String name;
    private List<CityComponent> streets = new ArrayList<>();

    public Neighborhood(String name) {
        this.name = name;
    }

    // Adds a street or subcomponent to the neighborhood
    @Override
    public void addComponent(CityComponent component) {
        streets.add(component);
    }
 // Removes a street or subcomponent from the neighborhood
    @Override
    public void removeComponent(CityComponent component) {
        streets.remove(component);
    }
 // Gets a street or subcomponent from the neighborhood
    @Override
    public CityComponent getComponent(int index) {
        return streets.get(index);
    }
 // Returns the name of the neighborhood
    @Override
    public String getName() {
        return name;
    }
 // Accepts a visitor to process sensors in the neighborhood
    @Override
    public void accept(SensorVisitor visitor) {
        for (CityComponent street : streets) {
            street.accept(visitor);
        }
    }
}

// Composite class – represents a street containing apartments

class Street implements CityComponent {
    private String name;
    private List<CityComponent> apartments = new ArrayList<>();
    
    public Street(String name) {
        this.name = name;
    }

    // Adds an apartment or subcomponent to the street
    @Override
    public void addComponent(CityComponent component) {
        apartments.add(component);
    }
 // Removes an apartment or subcomponent from the street
    @Override
    public void removeComponent(CityComponent component) {
        apartments.remove(component);
    }
 // Gets an apartment or subcomponent from the street
    @Override
    public CityComponent getComponent(int index) {
        return apartments.get(index);
    }
// Returns the name of the street
    @Override
    public String getName() {
        return name;
    }
// Accepts a visitor to process sensors in the street

    @Override
    public void accept(SensorVisitor visitor) {
        for (CityComponent apartment : apartments) {
            apartment.accept(visitor);
        }
    }
}



//  Leaf class – represents an apartment which contains sensors
class Apartment implements CityComponent {
    private String name;
    List<Sensor> sensors = new ArrayList<>();

    public Apartment(String name) {
        this.name = name;
    }

    public List<Sensor> getSensors() {
        return sensors;
    }

    public void addSensor(Sensor sensor) {
        sensors.add(sensor);
    }

    @Override
    public void addComponent(CityComponent component) {
        throw new UnsupportedOperationException("Cannot add component to Apartment");
    }

    @Override
    public void removeComponent(CityComponent component) {
        throw new UnsupportedOperationException("Cannot remove component from Apartment");
    }

    @Override
    public CityComponent getComponent(int index) {
        throw new UnsupportedOperationException("Cannot get subcomponent from Apartment");
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void accept(SensorVisitor visitor) {
        for (Sensor sensor : sensors) {
            visitor.visit(sensor);
        }
    }
}

//Leaf class - represents a pole which contains sensors
class Pole implements CityComponent {
    private String name;
    private List<Sensor> sensors = new ArrayList<>();

    public Pole(String name) {
        this.name = name;
    }

    public void addSensor(Sensor sensor) {
        sensors.add(sensor);
    }

    public List<Sensor> getSensors() {
        return sensors;
    }

    @Override
    public void addComponent(CityComponent component) {
        throw new UnsupportedOperationException("Cannot add component to Pole");
    }

    @Override
    public void removeComponent(CityComponent component) {
        throw new UnsupportedOperationException("Cannot remove component from Pole");
    }

    @Override
    public CityComponent getComponent(int index) {
        throw new UnsupportedOperationException("Cannot get subcomponent from Pole");
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void accept(SensorVisitor visitor) {
        for (Sensor sensor : sensors) {
            visitor.visit(sensor);
        }
    }
} 