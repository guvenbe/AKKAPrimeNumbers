package com.vp;

import akka.actor.typed.ActorSystem;
import akka.actor.typed.javadsl.AskPattern;

import java.math.BigInteger;
import java.time.Duration;
import java.util.SortedSet;
import java.util.concurrent.CompletionStage;

public class Main {
    public static void main(String[] args) {

        ActorSystem<ManagerBehavior.Command> bigPrimes = ActorSystem.create(ManagerBehavior.create(), "BigPrimes");

        CompletionStage<SortedSet<BigInteger>> result =
                AskPattern.ask(bigPrimes,
                        (me) -> new ManagerBehavior.InstructionCommmand("start", me),
                Duration.ofSeconds(60),
                bigPrimes.scheduler()
        );
        result.whenComplete(
                (reply,failure) ->{
                    if(reply != null){
                        reply.forEach(System.out::println);
                    }
                    else {
                        System.out.println("The system did not respond in time");
                    }
                    bigPrimes.terminate();
                }
        );
    }
}
