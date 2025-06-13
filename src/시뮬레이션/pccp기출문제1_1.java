package 시뮬레이션;

import java.util.*;

public class pccp기출문제1_1 {

    class Solution {
        public int solution(int[] bandage, int maxHp, int[][] attacks) {

            int nowHp = maxHp;

            Deque<Attack> queue = new ArrayDeque<>();

            for(int[] attack : attacks){
                queue.add(new Attack(attack[0], attack[1]));
            }

            int healTime = 0;

            for(int time=0; time<=attacks[attacks.length-1][0]; time++){

                // 1. 공격 받기
                if(queue.peekFirst().time == time){
                    Attack nowAttack = queue.pollFirst();
                    //System.out.println("time : " + nowAttack.time + " damage : " + nowAttack.damage);

                    nowHp -= nowAttack.damage;
                    if(nowHp <= 0) return -1;       // 사망이면 끝

                    healTime = 0;

                    continue;
                }

                // 2. 힐
                healTime ++;
                if(nowHp < maxHp){
                    int healPoint = 0;
                    healPoint += bandage[1];
                    if(healTime == bandage[0]){
                        healPoint += bandage[2];
                        healTime = 0;
                    }

                    if(healPoint + nowHp > maxHp){
                        nowHp = maxHp;
                    }else{
                        nowHp += healPoint;
                    }
                }
            }

            return nowHp;
        }

        private class Attack{
            int time;
            int damage;

            Attack(int time, int damage){
                this.time = time;
                this.damage = damage;
            }
        }
    }
}
