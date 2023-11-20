package lotto.controller

import lotto.domain.LottoCreateMachine
import lotto.domain.WinningType
import lotto.domain.LottoRateOfReturnCalculator
import lotto.domain.model.vo.BuyPrice
import lotto.view.InputView
import lotto.view.ResultView

object LottoController {

    /**
     * 로또 기계 시작
     * */
    fun runLottoMachine() {
        val buyPrice = BuyPrice.valueOf(InputView.getInputValue(InputView.InputType.BUY_LOTTO_PRICE))
        val lottoList = LottoCreateMachine.buyLottoList(buyPrice)

        InputView.drawLottoList(lottoList)

        val winningNumberText = InputView.getInputValue(InputView.InputType.LAST_WEEK_WINNING_NUMBER)
        val winningBonusNumberText = InputView.getInputValue(InputView.InputType.LAST_WEEK_WINNING_NUMBER_BONUS)
        val lottoResultList = WinningType.runDrawLottos(winningNumberText, winningBonusNumberText, lottoList)

        ResultView.drawLottoMatchResultList(lottoResultList)

        val rateOfReturn = LottoRateOfReturnCalculator.rateOfReturn(buyPrice, lottoResultList)
        ResultView.drawLottoMatchResultRateOfReturn(rateOfReturn)
    }
}
