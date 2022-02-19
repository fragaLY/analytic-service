package by.vk.betting.api.analytic.dto.result;

import java.util.Objects;

public class ResultHolder {

  private final String team;
  private int amount;

  public ResultHolder(String team, int amount) {
    this.team = team;
    this.amount = amount;
  }

  @Override
  public String toString() {
    return "ResultHolder{" + "team='" + team + '\'' + ", amount=" + amount + '}';
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ResultHolder that = (ResultHolder) o;

    if (amount != that.amount) return false;
    return Objects.equals(team, that.team);
  }

  @Override
  public int hashCode() {
    int result = team != null ? team.hashCode() : 0;
    result = 31 * result + amount;
    return result;
  }
}
