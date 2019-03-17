package TJasn.virtualMachine;

import TJasn.TJ;

import static TJasn.virtualMachine.CodeInterpreter.*;

public class CALLSTATMETHODinstr extends OneOperandInstruction {

  void execute ()
  {
    /* ???????? *///done
    TJ.data[ASP++ - POINTERTAG] = PC;
    PC = operand;
  }

  public CALLSTATMETHODinstr (int startAddr)
  {
    super(startAddr, "CALLSTATMETHOD");
  }
}
