package TJasn.virtualMachine;

import static TJasn.virtualMachine.CodeInterpreter.*;

public class PUSHSTATADDRinstr extends OneOperandInstruction {

  void execute ()
  {
    /* ???????? *///done
    EXPRSTACK[ESP++] = POINTERTAG + operand;
  }

  public PUSHSTATADDRinstr (int offset)
  {
    super(offset, "PUSHSTATADDR");
  }
}

