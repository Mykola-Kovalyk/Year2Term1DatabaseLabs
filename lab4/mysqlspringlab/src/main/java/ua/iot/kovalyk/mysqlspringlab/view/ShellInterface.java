package ua.iot.kovalyk.mysqlspringlab.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Time;

import bsh.EvalError;
import bsh.Interpreter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.iot.kovalyk.mysqlspringlab.service.*;
import ua.iot.kovalyk.mysqlspringlab.domain.WorkingHours;

@Component
public class ShellInterface {

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private ManufacturerService manufacturerService;

    @Autowired
    private PartService partService;

    @Autowired
    private RepairCaseService repairCaseService;

    @Autowired
    private RepairmanService repairmanService;

    @Autowired
    private RepairOptionService repairOptionService;

    @Autowired
    private ReplacedPartService replacedPartService;

    @Autowired
    private WorkingHourService workingHourService;

    public void mainLoop() {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        String command = null;
        Interpreter interpreter =  new Interpreter();

        try {
            interpreter.eval("import java.sql.*;");
            interpreter.eval("import ua.iot.kovalyk.mysqlspringlab.domain.*;");
            interpreter.set("devices", deviceService);
            interpreter.set("manufacturers", manufacturerService);
            interpreter.set("parts", partService);
            interpreter.set("repairCases", repairCaseService);
            interpreter.set("repairmen", repairmanService);
            interpreter.set("repairOptions", repairOptionService);
            interpreter.set("replacedParts", replacedPartService);
            interpreter.set("workingHours", workingHourService);
            interpreter.set("output", null);
        } catch (EvalError e) {
            System.out.println("Exception while setting up environment:\n" + e);
        }

        do {
            try {
                System.out.print(" > ");
                command = reader.readLine();
                if(command == "exit") continue;

                if(command != null && command.length() > 0) {
                    interpreter.eval("output = " + command + ";");
                    System.out.println(interpreter.get("output"));
                }
            } catch (IOException e) {
                System.out.println("Exception while reading command:\n" + e);
            } catch (EvalError e) {
                System.out.println("Exception while executing command:\n" + e);
            }
        } while (command != "exit");

    }
}
