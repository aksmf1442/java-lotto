package lotto.domain.seller;

import java.util.List;
import lotto.domain.lotto.LottoRanking;
import lotto.domain.lotto.WinningLotto;
import lotto.dto.LottoCount;

public class LottoMachine implements Seller{
    private static final int LOTTO_AMOUNT = 1000;

    private final WinningLotto winningLotto;

    public LottoMachine(WinningLotto winningLotto) {
        this.winningLotto = winningLotto;
    }

    @Override
    public int findLottoCountByMoney(int money) {
        validateMoney(money);
        return money / LOTTO_AMOUNT;
    }

    private void validateMoney(int money) {
        if (money % LOTTO_AMOUNT != 0) {
            throw new IllegalArgumentException("[ERROR] 금액이 " + LOTTO_AMOUNT + "로 나누어 떨어지지 않습니다.");
        }
    }

    public int compareNumbers(List<Integer> numbers) {
        List<Integer> winningLottoNumbers = winningLotto.getLotto().getNumbers();
        int count = 0;
        for (int number : numbers) {
            if (winningLottoNumbers.contains(number)) {
                count++;
            }
        }
        return count;
    }

    @Override
    public boolean compareBonusNumber(List<Integer> numbers) {
        return numbers.contains(winningLotto.getBonus());
    }

    @Override
    public LottoRanking findLottoRanking(LottoCount lottoCount) {
        return LottoRanking.of(lottoCount);
    }

    @Override
    public int calculateTotalWinningAmount(List<LottoRanking> lottoRankings) {
        return 0;
    }
}
