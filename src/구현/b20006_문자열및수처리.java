package 구현;

import java.io.*;
import java.util.*;

public class b20006_문자열및수처리 {
    private static int N, maxMember;
    private static List<Room> rooms = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        maxMember = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int level = Integer.parseInt(st.nextToken());
            String name = st.nextToken();

            boolean isEnter = false;

            for (Room now : rooms) {
                if (now.cnt <= 0) {
                    continue;
                }

                if (now.level - 10 <= level && level <= now.level + 10) { // 범위 검사 수정
                    now.addMember(level, name);
                    isEnter = true;
                    break;
                }
            }

            if (!isEnter) {
                rooms.add(new Room(level, maxMember));
                rooms.get(rooms.size() - 1).addMember(level, name); // 중복 감소 방지
            }
        }

        for (Room room : rooms) {
            if (room.cnt <= 0) {
                System.out.println("Started!");
            } else {
                System.out.println("Waiting!");
            }

            Collections.sort(room.members, Comparator.comparing(m -> m.name)); // 정렬 코드 유지

            for (Member member : room.members) {
                System.out.println(member.level + " " + member.name);
            }
        }
    }

    private static class Room {
        int level;
        int cnt;
        List<Member> members = new ArrayList<>();

        Room(int level, int cnt) {
            this.level = level;
            this.cnt = cnt;
        }

        public void addMember(int level, String name) {
            members.add(new Member(level, name));
            cnt--; // cnt 감소는 여기서 수행
        }
    }

    private static class Member {
        int level;
        String name;

        Member(int level, String name) {
            this.level = level;
            this.name = name;
        }
    }
}

