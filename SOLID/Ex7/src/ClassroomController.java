public class ClassroomController {
    private final DeviceRegistry reg;

    public ClassroomController(DeviceRegistry reg) { this.reg = reg; }

    public void startClass() {
        PowerControllable pj = reg.getFirst(PowerControllable.class);
        pj.powerOn();
        reg.getFirst(InputConnectable.class).connectInput("HDMI-1");

        reg.getFirst(BrightnessControllable.class).setBrightness(60);
        reg.getFirst(TemperatureControllable.class).setTemperatureC(24);

        System.out.println("Attendance scanned: present=" + reg.getFirst(AttendanceScannable.class).scanAttendance());
    }

    public void endClass() {
        System.out.println("Shutdown sequence:");
        for (PowerControllable d : reg.getAll(PowerControllable.class)) {
            d.powerOff();
        }
    }
}
