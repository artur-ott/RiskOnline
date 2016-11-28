trait SRisk

object ScalaRisk {
  private[this] var risk:Risk = _
  
  private final class Risk extends SRisk {
    
  }

  def getInstance(): SRisk = 
    if (this.risk != null)
      this.risk.asInstanceOf[Risk] 
    else {
      this.risk = new Risk
      return this.risk
    }
  
  def main(args: Array[String]): Unit = {
    this.getInstance();
  }
}