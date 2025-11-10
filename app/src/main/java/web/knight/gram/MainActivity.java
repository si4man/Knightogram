package web.knight.gram;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private ListView chatListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        setContentView(R.layout.activity_main);
        View root = findViewById(R.id.root);
        ViewCompat.setOnApplyWindowInsetsListener(root, (v, insets) -> {
            Insets sys = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            View toolbar = findViewById(R.id.toolbar);
            toolbar.setPadding(toolbar.getPaddingLeft(), toolbar.getPaddingTop() + sys.top, toolbar.getPaddingRight(), toolbar.getPaddingBottom());
            return insets;
        });

        chatListView = findViewById(R.id.listView);
        List<Map<String, Object>> chats = ChatRepository.get(this);
        String[] fromChat = {"chat_avatar", "chat_name", "chat_time", "chat_preview", "chat_unread"};
        int[] to = {R.id.avatar, R.id.name, R.id.time, R.id.preview, R.id.unread};
        SimpleAdapter chatAdapter = new SimpleAdapter(this, chats, R.layout.chat, fromChat, to);

        chatAdapter.setViewBinder((view, value, textRep) -> {
            int id = view.getId();
            if (id == R.id.avatar) {
                ImageView avatarView = (ImageView) view;
                GradientDrawable circle = new GradientDrawable();
                circle.setShape(GradientDrawable.OVAL);
                circle.setColor(getColor(R.color.avatar_background));
                avatarView.setBackground(circle);
                avatarView.setImageResource(R.drawable.ic_person);
                return true;
            }
            if (id == R.id.unread) {
                TextView unreadCounter = (TextView) view;
                int count = 0;
                if (value instanceof Integer) count = (Integer) value;
                else if (value instanceof String) try { count = Integer.parseInt((String) value); } catch (Exception ignored) {}
                if (count > 0) {
                    GradientDrawable badge = new GradientDrawable(
                            GradientDrawable.Orientation.LEFT_RIGHT,
                            new int[]{getColor(R.color.badge_gradient_start), getColor(R.color.badge_gradient_end)}
                    );
                    badge.setCornerRadius(100f);
                    unreadCounter.setBackground(badge);
                    unreadCounter.setText(String.valueOf(count));
                    unreadCounter.setTextColor(getColor(R.color.badge_text_color));
                    unreadCounter.setVisibility(View.VISIBLE);
                } else {
                    unreadCounter.setText(null);
                    unreadCounter.setBackground(null);
                    unreadCounter.setVisibility(View.INVISIBLE);
                }
                return true;
            }
            return false;
        });

        chatListView.setAdapter(chatAdapter);
        chatListView.setOnItemClickListener((parent, view, position, id) -> {
            @SuppressWarnings("unchecked")
            Map<String, Object> item = (Map<String, Object>) parent.getItemAtPosition(position);
            String name = String.valueOf(item.get("chat_name"));
            Toast.makeText(MainActivity.this, "Opened dialog with " + name, Toast.LENGTH_SHORT).show();
        });
    }
}
