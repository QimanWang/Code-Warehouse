package TJasn.virtualMachine;

import TJ1asn.SourceFileErrorException;
import TJasn.TJ;

import static TJasn.virtualMachine.CodeInterpreter.*;

public class PUSHNUMinstr extends OneOperandInstruction {

  void execute ()
  {
     /* ???????? *///done
    EXPRSTACK[ESP++] = operand;
  }

  public PUSHNUMinstr (int value) throws SourceFileErrorException
  {
    super(value, "PUSHNUM");
    if (operand >= POINTERTAG + TJ.HEAP_START)
      throw new SourceFileErrorException("Integer constant is too large.");
  }
}

