package ru.job4j.srp;

import lombok.AllArgsConstructor;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@AllArgsConstructor
public class XMLReport implements Report {
    private final static String SEPARATOR = System.lineSeparator();
    private Store store;

    @Override
    public String generate(Predicate<Employee> filter) {
        try {
            JAXBContext context = JAXBContext.newInstance(Employee.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            List<Employee> employees = store.findBy(filter).stream()
                    .sorted((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()) * (-1))
                    .collect(Collectors.toList());
            try (StringWriter writer = new StringWriter()) {
                for (Employee employee : employees) {
                    marshaller.marshal(employee, writer);
                }
                return writer.getBuffer().toString();
            }
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        XMLReport report = new XMLReport(new MemStore());
        System.out.println(report.generate(em -> true));
    }
}