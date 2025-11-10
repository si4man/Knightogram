package web.knight.gram;
import android.content.Context;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatRepository {
    private ChatRepository() {}

    public static List<Map<String, Object>> get(Context context) {
        List<Map<String, Object>> data = new ArrayList<>();

        HashMap<String, Object> chat1 = new HashMap<>();
        chat1.put("chat_avatar", R.drawable.ic_person);
        chat1.put("chat_name", "Учебный отдел ШАГ");
        chat1.put("chat_time", "08:51");
        chat1.put("chat_preview", "Здравствуйте, вас нет на паре аж минуту.");
        chat1.put("chat_unread", 1);
        data.add(chat1);

        HashMap<String, Object> chat2 = new HashMap<>();
        chat2.put("chat_avatar", R.drawable.ic_person);
        chat2.put("chat_name", "Мама");
        chat2.put("chat_time", "18:34");
        chat2.put("chat_preview", "Ты покушал????");
        chat2.put("chat_unread", 0);
        data.add(chat2);

        HashMap<String, Object> chat3 = new HashMap<>();
        chat3.put("chat_avatar", R.drawable.ic_person);
        chat3.put("chat_name", "Мой будущий начальник");
        chat3.put("chat_time", "15:56");
        chat3.put("chat_preview", "Жду отчёт до сегодняшнего вечера");
        chat3.put("chat_unread", 5);
        data.add(chat3);

        HashMap<String, Object> chat4 = new HashMap<>();
        chat4.put("chat_avatar", R.drawable.ic_person);
        chat4.put("chat_name", "Брат");
        chat4.put("chat_time", "14:12");
        chat4.put("chat_preview", "Я забрал твой киткат, надеюсь ты не обидишься");
        chat4.put("chat_unread", 0);
        data.add(chat4);

        HashMap<String, Object> chat5 = new HashMap<>();
        chat5.put("chat_avatar", R.drawable.ic_person);
        chat5.put("chat_name", "Поддержка Knightogram");
        chat5.put("chat_time", "11:30");
        chat5.put("chat_preview", "Нам нужен ваш 2FA код для восстановления доступа к аккаунту.");
        chat5.put("chat_unread", 2);
        data.add(chat5);

        return data;
    }
}
