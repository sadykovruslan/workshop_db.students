import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class StudentSurnameStorage {
    private TreeMap <String, Set<Long>> surnamesTreeMap = new TreeMap<>();

    public void studentCreated (Long id, String surname){
        Set<Long> existingIds = surnamesTreeMap.getOrDefault(surname, new HashSet<>());
        existingIds.add(id);
        surnamesTreeMap.put(surname, existingIds);
    }

    public void studentDeleted (Long id, String surname) {
        surnamesTreeMap.get(surname).remove((id));
    }

    public void studentUpdate (Long id, String oldSurname, String newSurname){
        studentDeleted(id,oldSurname);
        studentCreated(id, newSurname);
    }

    /**
     * Данный метод возвращает уникальные идентификаторы студентов чьи фамилии меньше или равны переданной
     * @return set
     */
    public Set<Long> getStudentsBetweenSurnames(String surname1, String surname2){
        Set <Long> res = surnamesTreeMap.subMap(surname1, true, surname2, true)
                .values()
                .stream()
                .flatMap(longs -> longs.stream())
                .collect(Collectors.toSet());
        return res;
    }
}