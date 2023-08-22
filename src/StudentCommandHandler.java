public class StudentCommandHandler {
    private StudentStorage studentStorage = new StudentStorage();

    public void processCommand (Command command){
        Action action = command.getAction();
        switch (action){
            case CREATE -> processCreateCommand(command);
            case UPDATE -> processUpdateCommand(command);
            case DELETE -> processDeleteCommand(command);
            default -> System.out.println("Действие не поддерживается");
        }


        System.out.println("Обработка команды. действие " + command.getAction().name()
                + " данные" + command.getData());
    }

    private void processCreateCommand(Command command){
        String data = command.getData(); // строка с данными о студенте через запятую
        String[] dataArray = data.split(", "); // заполняем массив стрингами разделяя через запятую

        Student student = new Student(); // создаем нового студента

        // присваиваем атрибуты данными из массива
        student.setSurName(dataArray[0]);
        student.setName(dataArray[1]);
        student.setCourse(dataArray[2]);
        student.setCity(dataArray[3]);
        student.setAge(Integer.valueOf(dataArray[4]));

        studentStorage.createStudent(student);
        studentStorage.printAll();
    }

    public void processUpdateCommand (Command command){
        String data = command.getData(); // строка с данными о студенте через запятую
        String[] dataArray = data.split(", "); // заполняем массив стрингами разделяя через запятую

        Long id = Long.valueOf(dataArray[0]);

        // присваиваем атрибуты данными из массива
        Student student = new Student();
        student.setSurName(dataArray[1]);
        student.setName(dataArray[2]);
        student.setCourse(dataArray[3]);
        student.setCity(dataArray[4]);
        student.setAge(Integer.valueOf(dataArray[5]));

        studentStorage.updateStudent(id, student);
        studentStorage.printAll();
    }

    public void processDeleteCommand (Command command) {
        String data = command.getData(); // строка с данными о студенте через запятую
        Long id = Long.valueOf(data);

        studentStorage.deleteStudent(id);
        studentStorage.printAll();
    }
}
