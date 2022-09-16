package skane.skaneshop.Auction.application;

import skane.skaneshop.Auction.infra.AuctionTestRepository;
import skane.skaneshop.domain.Auction;

import java.util.TimerTask;
import java.util.Timer;

/**
 * 새롭게 고안한 Auction 종료시간 체크
 */
public class AuctionTimeThread {


    public AuctionTimeThread(Auction auction, AuctionTestRepository auctionRepository) {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("옥션 종료");
                auctionRepository.remove(auction.getAuctionNumber());

            }
        };

        //1000 ==1초
        //예시로 1분 지정
        long delay= 1000L*60 ;

        Timer timer = new Timer();

        timer.schedule(task,delay);
    }


}
