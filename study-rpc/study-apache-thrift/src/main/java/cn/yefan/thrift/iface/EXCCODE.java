/**
 * Autogenerated by Thrift Compiler (0.10.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package cn.yefan.thrift.iface;


import java.util.Map;
import java.util.HashMap;
import org.apache.thrift.TEnum;

public enum EXCCODE implements org.apache.thrift.TEnum {
  PARAMNOTFOUND(2001),
  SERVICENOTFOUND(2002);

  private final int value;

  private EXCCODE(int value) {
    this.value = value;
  }

  /**
   * Get the integer value of this enum value, as defined in the Thrift IDL.
   */
  public int getValue() {
    return value;
  }

  /**
   * Find a the enum type by its integer value, as defined in the Thrift IDL.
   * @return null if the value is not found.
   */
  public static EXCCODE findByValue(int value) { 
    switch (value) {
      case 2001:
        return PARAMNOTFOUND;
      case 2002:
        return SERVICENOTFOUND;
      default:
        return null;
    }
  }
}
