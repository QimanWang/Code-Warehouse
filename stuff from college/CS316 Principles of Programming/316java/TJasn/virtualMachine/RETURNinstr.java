package TJasn.virtualMachine;

import TJasn.TJ;

import static TJasn.virtualMachine.CodeInterpreter.*;

public class RETURNinstr extends OneOperandInstruction {

  void execute ()
  {
    /* ???????? *///done
    ASP = FP+1;
    FP = TJ.data[--ASP - POINTERTAG];
    PC = TJ.data[--ASP - POINTERTAG];
    ASP-=operand;
  }

  public RETURNinstr (int operand)
  {
    super(operand, "RETURN");
  }
}

