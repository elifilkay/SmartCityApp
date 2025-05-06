
// Command interface - to be implemented by concrete commands
interface Command {
    void execute();
}
// Command to query sensors for malfunctioning status
class QuerySensorsCommand implements Command {
    private DataMonitoringDivision dataMonitoringDivision;

    public QuerySensorsCommand(DataMonitoringDivision dataMonitoringDivision) {
        this.dataMonitoringDivision = dataMonitoringDivision;
    }
 // Execute the command : checks for
    @Override
    public void execute() {
        int malfunctioningCount = dataMonitoringDivision.checkMalfunctioningSensors();
        System.out.println("Query completed-Total malfunctioning sensors: " + malfunctioningCount);
    }
}
// Command to reset malfunctioning sensors in a neighborhood
class ResetSensorsCommand implements Command {
    private Neighborhood neighborhood;

    public ResetSensorsCommand(Neighborhood neighborhood) {
        this.neighborhood = neighborhood;
    }
    // Executes the command: visits and resets malfunctioning sensors
    @Override
    public void execute() {
        ResetSensorVisitor visitor = new ResetSensorVisitor();
        neighborhood.accept(visitor);
        System.out.println("Reset command executed on all malfunctioning sensors in " + neighborhood.getName());
    }
} 