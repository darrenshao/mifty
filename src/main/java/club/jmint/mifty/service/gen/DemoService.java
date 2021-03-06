/**
 * Autogenerated by Thrift Compiler (0.9.3)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package club.jmint.mifty.service.gen;

import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;

import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.server.AbstractNonblockingServer.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import javax.annotation.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked"})
@Generated(value = "Autogenerated by Thrift Compiler (0.9.3)", date = "2016-05-26")
public class DemoService {

  public interface Iface {

    public String demoSay(String params, boolean isEncrypt) throws org.apache.thrift.TException;

  }

  public interface AsyncIface {

    public void demoSay(String params, boolean isEncrypt, org.apache.thrift.async.AsyncMethodCallback resultHandler) throws org.apache.thrift.TException;

  }

  public static class Client extends org.apache.thrift.TServiceClient implements Iface {
    public static class Factory implements org.apache.thrift.TServiceClientFactory<Client> {
      public Factory() {}
      public Client getClient(org.apache.thrift.protocol.TProtocol prot) {
        return new Client(prot);
      }
      public Client getClient(org.apache.thrift.protocol.TProtocol iprot, org.apache.thrift.protocol.TProtocol oprot) {
        return new Client(iprot, oprot);
      }
    }

    public Client(org.apache.thrift.protocol.TProtocol prot)
    {
      super(prot, prot);
    }

    public Client(org.apache.thrift.protocol.TProtocol iprot, org.apache.thrift.protocol.TProtocol oprot) {
      super(iprot, oprot);
    }

    public String demoSay(String params, boolean isEncrypt) throws org.apache.thrift.TException
    {
      send_demoSay(params, isEncrypt);
      return recv_demoSay();
    }

    public void send_demoSay(String params, boolean isEncrypt) throws org.apache.thrift.TException
    {
      demoSay_args args = new demoSay_args();
      args.setParams(params);
      args.setIsEncrypt(isEncrypt);
      sendBase("demoSay", args);
    }

    public String recv_demoSay() throws org.apache.thrift.TException
    {
      demoSay_result result = new demoSay_result();
      receiveBase(result, "demoSay");
      if (result.isSetSuccess()) {
        return result.success;
      }
      throw new org.apache.thrift.TApplicationException(org.apache.thrift.TApplicationException.MISSING_RESULT, "demoSay failed: unknown result");
    }

  }
  public static class AsyncClient extends org.apache.thrift.async.TAsyncClient implements AsyncIface {
    public static class Factory implements org.apache.thrift.async.TAsyncClientFactory<AsyncClient> {
      private org.apache.thrift.async.TAsyncClientManager clientManager;
      private org.apache.thrift.protocol.TProtocolFactory protocolFactory;
      public Factory(org.apache.thrift.async.TAsyncClientManager clientManager, org.apache.thrift.protocol.TProtocolFactory protocolFactory) {
        this.clientManager = clientManager;
        this.protocolFactory = protocolFactory;
      }
      public AsyncClient getAsyncClient(org.apache.thrift.transport.TNonblockingTransport transport) {
        return new AsyncClient(protocolFactory, clientManager, transport);
      }
    }

    public AsyncClient(org.apache.thrift.protocol.TProtocolFactory protocolFactory, org.apache.thrift.async.TAsyncClientManager clientManager, org.apache.thrift.transport.TNonblockingTransport transport) {
      super(protocolFactory, clientManager, transport);
    }

    public void demoSay(String params, boolean isEncrypt, org.apache.thrift.async.AsyncMethodCallback resultHandler) throws org.apache.thrift.TException {
      checkReady();
      demoSay_call method_call = new demoSay_call(params, isEncrypt, resultHandler, this, ___protocolFactory, ___transport);
      this.___currentMethod = method_call;
      ___manager.call(method_call);
    }

    public static class demoSay_call extends org.apache.thrift.async.TAsyncMethodCall {
      private String params;
      private boolean isEncrypt;
      public demoSay_call(String params, boolean isEncrypt, org.apache.thrift.async.AsyncMethodCallback resultHandler, org.apache.thrift.async.TAsyncClient client, org.apache.thrift.protocol.TProtocolFactory protocolFactory, org.apache.thrift.transport.TNonblockingTransport transport) throws org.apache.thrift.TException {
        super(client, protocolFactory, transport, resultHandler, false);
        this.params = params;
        this.isEncrypt = isEncrypt;
      }

      public void write_args(org.apache.thrift.protocol.TProtocol prot) throws org.apache.thrift.TException {
        prot.writeMessageBegin(new org.apache.thrift.protocol.TMessage("demoSay", org.apache.thrift.protocol.TMessageType.CALL, 0));
        demoSay_args args = new demoSay_args();
        args.setParams(params);
        args.setIsEncrypt(isEncrypt);
        args.write(prot);
        prot.writeMessageEnd();
      }

      public String getResult() throws org.apache.thrift.TException {
        if (getState() != org.apache.thrift.async.TAsyncMethodCall.State.RESPONSE_READ) {
          throw new IllegalStateException("Method call not finished!");
        }
        org.apache.thrift.transport.TMemoryInputTransport memoryTransport = new org.apache.thrift.transport.TMemoryInputTransport(getFrameBuffer().array());
        org.apache.thrift.protocol.TProtocol prot = client.getProtocolFactory().getProtocol(memoryTransport);
        return (new Client(prot)).recv_demoSay();
      }
    }

  }

  public static class Processor<I extends Iface> extends org.apache.thrift.TBaseProcessor<I> implements org.apache.thrift.TProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(Processor.class.getName());
    public Processor(I iface) {
      super(iface, getProcessMap(new HashMap<String, org.apache.thrift.ProcessFunction<I, ? extends org.apache.thrift.TBase>>()));
    }

    protected Processor(I iface, Map<String,  org.apache.thrift.ProcessFunction<I, ? extends  org.apache.thrift.TBase>> processMap) {
      super(iface, getProcessMap(processMap));
    }

    private static <I extends Iface> Map<String,  org.apache.thrift.ProcessFunction<I, ? extends  org.apache.thrift.TBase>> getProcessMap(Map<String,  org.apache.thrift.ProcessFunction<I, ? extends  org.apache.thrift.TBase>> processMap) {
      processMap.put("demoSay", new demoSay());
      return processMap;
    }

    public static class demoSay<I extends Iface> extends org.apache.thrift.ProcessFunction<I, demoSay_args> {
      public demoSay() {
        super("demoSay");
      }

      public demoSay_args getEmptyArgsInstance() {
        return new demoSay_args();
      }

      protected boolean isOneway() {
        return false;
      }

      public demoSay_result getResult(I iface, demoSay_args args) throws org.apache.thrift.TException {
        demoSay_result result = new demoSay_result();
        result.success = iface.demoSay(args.params, args.isEncrypt);
        return result;
      }
    }

  }

  public static class AsyncProcessor<I extends AsyncIface> extends org.apache.thrift.TBaseAsyncProcessor<I> {
    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncProcessor.class.getName());
    public AsyncProcessor(I iface) {
      super(iface, getProcessMap(new HashMap<String, org.apache.thrift.AsyncProcessFunction<I, ? extends org.apache.thrift.TBase, ?>>()));
    }

    protected AsyncProcessor(I iface, Map<String,  org.apache.thrift.AsyncProcessFunction<I, ? extends  org.apache.thrift.TBase, ?>> processMap) {
      super(iface, getProcessMap(processMap));
    }

    private static <I extends AsyncIface> Map<String,  org.apache.thrift.AsyncProcessFunction<I, ? extends  org.apache.thrift.TBase,?>> getProcessMap(Map<String,  org.apache.thrift.AsyncProcessFunction<I, ? extends  org.apache.thrift.TBase, ?>> processMap) {
      processMap.put("demoSay", new demoSay());
      return processMap;
    }

    public static class demoSay<I extends AsyncIface> extends org.apache.thrift.AsyncProcessFunction<I, demoSay_args, String> {
      public demoSay() {
        super("demoSay");
      }

      public demoSay_args getEmptyArgsInstance() {
        return new demoSay_args();
      }

      public AsyncMethodCallback<String> getResultHandler(final AsyncFrameBuffer fb, final int seqid) {
        final org.apache.thrift.AsyncProcessFunction fcall = this;
        return new AsyncMethodCallback<String>() { 
          public void onComplete(String o) {
            demoSay_result result = new demoSay_result();
            result.success = o;
            try {
              fcall.sendResponse(fb,result, org.apache.thrift.protocol.TMessageType.REPLY,seqid);
              return;
            } catch (Exception e) {
              LOGGER.error("Exception writing to internal frame buffer", e);
            }
            fb.close();
          }
          public void onError(Exception e) {
            byte msgType = org.apache.thrift.protocol.TMessageType.REPLY;
            org.apache.thrift.TBase msg;
            demoSay_result result = new demoSay_result();
            {
              msgType = org.apache.thrift.protocol.TMessageType.EXCEPTION;
              msg = (org.apache.thrift.TBase)new org.apache.thrift.TApplicationException(org.apache.thrift.TApplicationException.INTERNAL_ERROR, e.getMessage());
            }
            try {
              fcall.sendResponse(fb,msg,msgType,seqid);
              return;
            } catch (Exception ex) {
              LOGGER.error("Exception writing to internal frame buffer", ex);
            }
            fb.close();
          }
        };
      }

      protected boolean isOneway() {
        return false;
      }

      public void start(I iface, demoSay_args args, org.apache.thrift.async.AsyncMethodCallback<String> resultHandler) throws TException {
        iface.demoSay(args.params, args.isEncrypt,resultHandler);
      }
    }

  }

  public static class demoSay_args implements org.apache.thrift.TBase<demoSay_args, demoSay_args._Fields>, java.io.Serializable, Cloneable, Comparable<demoSay_args>   {
    private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("demoSay_args");

    private static final org.apache.thrift.protocol.TField PARAMS_FIELD_DESC = new org.apache.thrift.protocol.TField("params", org.apache.thrift.protocol.TType.STRING, (short)1);
    private static final org.apache.thrift.protocol.TField IS_ENCRYPT_FIELD_DESC = new org.apache.thrift.protocol.TField("isEncrypt", org.apache.thrift.protocol.TType.BOOL, (short)2);

    private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
    static {
      schemes.put(StandardScheme.class, new demoSay_argsStandardSchemeFactory());
      schemes.put(TupleScheme.class, new demoSay_argsTupleSchemeFactory());
    }

    public String params; // required
    public boolean isEncrypt; // required

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements org.apache.thrift.TFieldIdEnum {
      PARAMS((short)1, "params"),
      IS_ENCRYPT((short)2, "isEncrypt");

      private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

      static {
        for (_Fields field : EnumSet.allOf(_Fields.class)) {
          byName.put(field.getFieldName(), field);
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, or null if its not found.
       */
      public static _Fields findByThriftId(int fieldId) {
        switch(fieldId) {
          case 1: // PARAMS
            return PARAMS;
          case 2: // IS_ENCRYPT
            return IS_ENCRYPT;
          default:
            return null;
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, throwing an exception
       * if it is not found.
       */
      public static _Fields findByThriftIdOrThrow(int fieldId) {
        _Fields fields = findByThriftId(fieldId);
        if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
        return fields;
      }

      /**
       * Find the _Fields constant that matches name, or null if its not found.
       */
      public static _Fields findByName(String name) {
        return byName.get(name);
      }

      private final short _thriftId;
      private final String _fieldName;

      _Fields(short thriftId, String fieldName) {
        _thriftId = thriftId;
        _fieldName = fieldName;
      }

      public short getThriftFieldId() {
        return _thriftId;
      }

      public String getFieldName() {
        return _fieldName;
      }
    }

    // isset id assignments
    private static final int __ISENCRYPT_ISSET_ID = 0;
    private byte __isset_bitfield = 0;
    public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
    static {
      Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
      tmpMap.put(_Fields.PARAMS, new org.apache.thrift.meta_data.FieldMetaData("params", org.apache.thrift.TFieldRequirementType.DEFAULT, 
          new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
      tmpMap.put(_Fields.IS_ENCRYPT, new org.apache.thrift.meta_data.FieldMetaData("isEncrypt", org.apache.thrift.TFieldRequirementType.DEFAULT, 
          new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.BOOL)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(demoSay_args.class, metaDataMap);
    }

    public demoSay_args() {
    }

    public demoSay_args(
      String params,
      boolean isEncrypt)
    {
      this();
      this.params = params;
      this.isEncrypt = isEncrypt;
      setIsEncryptIsSet(true);
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public demoSay_args(demoSay_args other) {
      __isset_bitfield = other.__isset_bitfield;
      if (other.isSetParams()) {
        this.params = other.params;
      }
      this.isEncrypt = other.isEncrypt;
    }

    public demoSay_args deepCopy() {
      return new demoSay_args(this);
    }

    
    public void clear() {
      this.params = null;
      setIsEncryptIsSet(false);
      this.isEncrypt = false;
    }

    public String getParams() {
      return this.params;
    }

    public demoSay_args setParams(String params) {
      this.params = params;
      return this;
    }

    public void unsetParams() {
      this.params = null;
    }

    /** Returns true if field params is set (has been assigned a value) and false otherwise */
    public boolean isSetParams() {
      return this.params != null;
    }

    public void setParamsIsSet(boolean value) {
      if (!value) {
        this.params = null;
      }
    }

    public boolean isIsEncrypt() {
      return this.isEncrypt;
    }

    public demoSay_args setIsEncrypt(boolean isEncrypt) {
      this.isEncrypt = isEncrypt;
      setIsEncryptIsSet(true);
      return this;
    }

    public void unsetIsEncrypt() {
      __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __ISENCRYPT_ISSET_ID);
    }

    /** Returns true if field isEncrypt is set (has been assigned a value) and false otherwise */
    public boolean isSetIsEncrypt() {
      return EncodingUtils.testBit(__isset_bitfield, __ISENCRYPT_ISSET_ID);
    }

    public void setIsEncryptIsSet(boolean value) {
      __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __ISENCRYPT_ISSET_ID, value);
    }

    public void setFieldValue(_Fields field, Object value) {
      switch (field) {
      case PARAMS:
        if (value == null) {
          unsetParams();
        } else {
          setParams((String)value);
        }
        break;

      case IS_ENCRYPT:
        if (value == null) {
          unsetIsEncrypt();
        } else {
          setIsEncrypt((Boolean)value);
        }
        break;

      }
    }

    public Object getFieldValue(_Fields field) {
      switch (field) {
      case PARAMS:
        return getParams();

      case IS_ENCRYPT:
        return isIsEncrypt();

      }
      throw new IllegalStateException();
    }

    /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
      if (field == null) {
        throw new IllegalArgumentException();
      }

      switch (field) {
      case PARAMS:
        return isSetParams();
      case IS_ENCRYPT:
        return isSetIsEncrypt();
      }
      throw new IllegalStateException();
    }

    @Override
    public boolean equals(Object that) {
      if (that == null)
        return false;
      if (that instanceof demoSay_args)
        return this.equals((demoSay_args)that);
      return false;
    }

    public boolean equals(demoSay_args that) {
      if (that == null)
        return false;

      boolean this_present_params = true && this.isSetParams();
      boolean that_present_params = true && that.isSetParams();
      if (this_present_params || that_present_params) {
        if (!(this_present_params && that_present_params))
          return false;
        if (!this.params.equals(that.params))
          return false;
      }

      boolean this_present_isEncrypt = true;
      boolean that_present_isEncrypt = true;
      if (this_present_isEncrypt || that_present_isEncrypt) {
        if (!(this_present_isEncrypt && that_present_isEncrypt))
          return false;
        if (this.isEncrypt != that.isEncrypt)
          return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      List<Object> list = new ArrayList<Object>();

      boolean present_params = true && (isSetParams());
      list.add(present_params);
      if (present_params)
        list.add(params);

      boolean present_isEncrypt = true;
      list.add(present_isEncrypt);
      if (present_isEncrypt)
        list.add(isEncrypt);

      return list.hashCode();
    }

    
    public int compareTo(demoSay_args other) {
      if (!getClass().equals(other.getClass())) {
        return getClass().getName().compareTo(other.getClass().getName());
      }

      int lastComparison = 0;

      lastComparison = Boolean.valueOf(isSetParams()).compareTo(other.isSetParams());
      if (lastComparison != 0) {
        return lastComparison;
      }
      if (isSetParams()) {
        lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.params, other.params);
        if (lastComparison != 0) {
          return lastComparison;
        }
      }
      lastComparison = Boolean.valueOf(isSetIsEncrypt()).compareTo(other.isSetIsEncrypt());
      if (lastComparison != 0) {
        return lastComparison;
      }
      if (isSetIsEncrypt()) {
        lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.isEncrypt, other.isEncrypt);
        if (lastComparison != 0) {
          return lastComparison;
        }
      }
      return 0;
    }

    public _Fields fieldForId(int fieldId) {
      return _Fields.findByThriftId(fieldId);
    }

    public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
      schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
      schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder("demoSay_args(");
      boolean first = true;

      sb.append("params:");
      if (this.params == null) {
        sb.append("null");
      } else {
        sb.append(this.params);
      }
      first = false;
      if (!first) sb.append(", ");
      sb.append("isEncrypt:");
      sb.append(this.isEncrypt);
      first = false;
      sb.append(")");
      return sb.toString();
    }

    public void validate() throws org.apache.thrift.TException {
      // check for required fields
      // check for sub-struct validity
    }

    private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
      try {
        write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
      } catch (org.apache.thrift.TException te) {
        throw new java.io.IOException(te);
      }
    }

    private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
      try {
        // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
        __isset_bitfield = 0;
        read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
      } catch (org.apache.thrift.TException te) {
        throw new java.io.IOException(te);
      }
    }

    private static class demoSay_argsStandardSchemeFactory implements SchemeFactory {
      public demoSay_argsStandardScheme getScheme() {
        return new demoSay_argsStandardScheme();
      }
    }

    private static class demoSay_argsStandardScheme extends StandardScheme<demoSay_args> {

      public void read(org.apache.thrift.protocol.TProtocol iprot, demoSay_args struct) throws org.apache.thrift.TException {
        org.apache.thrift.protocol.TField schemeField;
        iprot.readStructBegin();
        while (true)
        {
          schemeField = iprot.readFieldBegin();
          if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
            break;
          }
          switch (schemeField.id) {
            case 1: // PARAMS
              if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
                struct.params = iprot.readString();
                struct.setParamsIsSet(true);
              } else { 
                org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
              }
              break;
            case 2: // IS_ENCRYPT
              if (schemeField.type == org.apache.thrift.protocol.TType.BOOL) {
                struct.isEncrypt = iprot.readBool();
                struct.setIsEncryptIsSet(true);
              } else { 
                org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
              }
              break;
            default:
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
          }
          iprot.readFieldEnd();
        }
        iprot.readStructEnd();

        // check for required fields of primitive type, which can't be checked in the validate method
        struct.validate();
      }

      public void write(org.apache.thrift.protocol.TProtocol oprot, demoSay_args struct) throws org.apache.thrift.TException {
        struct.validate();

        oprot.writeStructBegin(STRUCT_DESC);
        if (struct.params != null) {
          oprot.writeFieldBegin(PARAMS_FIELD_DESC);
          oprot.writeString(struct.params);
          oprot.writeFieldEnd();
        }
        oprot.writeFieldBegin(IS_ENCRYPT_FIELD_DESC);
        oprot.writeBool(struct.isEncrypt);
        oprot.writeFieldEnd();
        oprot.writeFieldStop();
        oprot.writeStructEnd();
      }

    }

    private static class demoSay_argsTupleSchemeFactory implements SchemeFactory {
      public demoSay_argsTupleScheme getScheme() {
        return new demoSay_argsTupleScheme();
      }
    }

    private static class demoSay_argsTupleScheme extends TupleScheme<demoSay_args> {

      
      public void write(org.apache.thrift.protocol.TProtocol prot, demoSay_args struct) throws org.apache.thrift.TException {
        TTupleProtocol oprot = (TTupleProtocol) prot;
        BitSet optionals = new BitSet();
        if (struct.isSetParams()) {
          optionals.set(0);
        }
        if (struct.isSetIsEncrypt()) {
          optionals.set(1);
        }
        oprot.writeBitSet(optionals, 2);
        if (struct.isSetParams()) {
          oprot.writeString(struct.params);
        }
        if (struct.isSetIsEncrypt()) {
          oprot.writeBool(struct.isEncrypt);
        }
      }

      
      public void read(org.apache.thrift.protocol.TProtocol prot, demoSay_args struct) throws org.apache.thrift.TException {
        TTupleProtocol iprot = (TTupleProtocol) prot;
        BitSet incoming = iprot.readBitSet(2);
        if (incoming.get(0)) {
          struct.params = iprot.readString();
          struct.setParamsIsSet(true);
        }
        if (incoming.get(1)) {
          struct.isEncrypt = iprot.readBool();
          struct.setIsEncryptIsSet(true);
        }
      }
    }

  }

  public static class demoSay_result implements org.apache.thrift.TBase<demoSay_result, demoSay_result._Fields>, java.io.Serializable, Cloneable, Comparable<demoSay_result>   {
    private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("demoSay_result");

    private static final org.apache.thrift.protocol.TField SUCCESS_FIELD_DESC = new org.apache.thrift.protocol.TField("success", org.apache.thrift.protocol.TType.STRING, (short)0);

    private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
    static {
      schemes.put(StandardScheme.class, new demoSay_resultStandardSchemeFactory());
      schemes.put(TupleScheme.class, new demoSay_resultTupleSchemeFactory());
    }

    public String success; // required

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements org.apache.thrift.TFieldIdEnum {
      SUCCESS((short)0, "success");

      private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

      static {
        for (_Fields field : EnumSet.allOf(_Fields.class)) {
          byName.put(field.getFieldName(), field);
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, or null if its not found.
       */
      public static _Fields findByThriftId(int fieldId) {
        switch(fieldId) {
          case 0: // SUCCESS
            return SUCCESS;
          default:
            return null;
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, throwing an exception
       * if it is not found.
       */
      public static _Fields findByThriftIdOrThrow(int fieldId) {
        _Fields fields = findByThriftId(fieldId);
        if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
        return fields;
      }

      /**
       * Find the _Fields constant that matches name, or null if its not found.
       */
      public static _Fields findByName(String name) {
        return byName.get(name);
      }

      private final short _thriftId;
      private final String _fieldName;

      _Fields(short thriftId, String fieldName) {
        _thriftId = thriftId;
        _fieldName = fieldName;
      }

      public short getThriftFieldId() {
        return _thriftId;
      }

      public String getFieldName() {
        return _fieldName;
      }
    }

    // isset id assignments
    public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
    static {
      Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
      tmpMap.put(_Fields.SUCCESS, new org.apache.thrift.meta_data.FieldMetaData("success", org.apache.thrift.TFieldRequirementType.DEFAULT, 
          new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(demoSay_result.class, metaDataMap);
    }

    public demoSay_result() {
    }

    public demoSay_result(
      String success)
    {
      this();
      this.success = success;
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public demoSay_result(demoSay_result other) {
      if (other.isSetSuccess()) {
        this.success = other.success;
      }
    }

    public demoSay_result deepCopy() {
      return new demoSay_result(this);
    }

    
    public void clear() {
      this.success = null;
    }

    public String getSuccess() {
      return this.success;
    }

    public demoSay_result setSuccess(String success) {
      this.success = success;
      return this;
    }

    public void unsetSuccess() {
      this.success = null;
    }

    /** Returns true if field success is set (has been assigned a value) and false otherwise */
    public boolean isSetSuccess() {
      return this.success != null;
    }

    public void setSuccessIsSet(boolean value) {
      if (!value) {
        this.success = null;
      }
    }

    public void setFieldValue(_Fields field, Object value) {
      switch (field) {
      case SUCCESS:
        if (value == null) {
          unsetSuccess();
        } else {
          setSuccess((String)value);
        }
        break;

      }
    }

    public Object getFieldValue(_Fields field) {
      switch (field) {
      case SUCCESS:
        return getSuccess();

      }
      throw new IllegalStateException();
    }

    /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
      if (field == null) {
        throw new IllegalArgumentException();
      }

      switch (field) {
      case SUCCESS:
        return isSetSuccess();
      }
      throw new IllegalStateException();
    }

    @Override
    public boolean equals(Object that) {
      if (that == null)
        return false;
      if (that instanceof demoSay_result)
        return this.equals((demoSay_result)that);
      return false;
    }

    public boolean equals(demoSay_result that) {
      if (that == null)
        return false;

      boolean this_present_success = true && this.isSetSuccess();
      boolean that_present_success = true && that.isSetSuccess();
      if (this_present_success || that_present_success) {
        if (!(this_present_success && that_present_success))
          return false;
        if (!this.success.equals(that.success))
          return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      List<Object> list = new ArrayList<Object>();

      boolean present_success = true && (isSetSuccess());
      list.add(present_success);
      if (present_success)
        list.add(success);

      return list.hashCode();
    }

    
    public int compareTo(demoSay_result other) {
      if (!getClass().equals(other.getClass())) {
        return getClass().getName().compareTo(other.getClass().getName());
      }

      int lastComparison = 0;

      lastComparison = Boolean.valueOf(isSetSuccess()).compareTo(other.isSetSuccess());
      if (lastComparison != 0) {
        return lastComparison;
      }
      if (isSetSuccess()) {
        lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.success, other.success);
        if (lastComparison != 0) {
          return lastComparison;
        }
      }
      return 0;
    }

    public _Fields fieldForId(int fieldId) {
      return _Fields.findByThriftId(fieldId);
    }

    public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
      schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
      schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
      }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder("demoSay_result(");
      boolean first = true;

      sb.append("success:");
      if (this.success == null) {
        sb.append("null");
      } else {
        sb.append(this.success);
      }
      first = false;
      sb.append(")");
      return sb.toString();
    }

    public void validate() throws org.apache.thrift.TException {
      // check for required fields
      // check for sub-struct validity
    }

    private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
      try {
        write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
      } catch (org.apache.thrift.TException te) {
        throw new java.io.IOException(te);
      }
    }

    private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
      try {
        read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
      } catch (org.apache.thrift.TException te) {
        throw new java.io.IOException(te);
      }
    }

    private static class demoSay_resultStandardSchemeFactory implements SchemeFactory {
      public demoSay_resultStandardScheme getScheme() {
        return new demoSay_resultStandardScheme();
      }
    }

    private static class demoSay_resultStandardScheme extends StandardScheme<demoSay_result> {

      public void read(org.apache.thrift.protocol.TProtocol iprot, demoSay_result struct) throws org.apache.thrift.TException {
        org.apache.thrift.protocol.TField schemeField;
        iprot.readStructBegin();
        while (true)
        {
          schemeField = iprot.readFieldBegin();
          if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
            break;
          }
          switch (schemeField.id) {
            case 0: // SUCCESS
              if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
                struct.success = iprot.readString();
                struct.setSuccessIsSet(true);
              } else { 
                org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
              }
              break;
            default:
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
          }
          iprot.readFieldEnd();
        }
        iprot.readStructEnd();

        // check for required fields of primitive type, which can't be checked in the validate method
        struct.validate();
      }

      public void write(org.apache.thrift.protocol.TProtocol oprot, demoSay_result struct) throws org.apache.thrift.TException {
        struct.validate();

        oprot.writeStructBegin(STRUCT_DESC);
        if (struct.success != null) {
          oprot.writeFieldBegin(SUCCESS_FIELD_DESC);
          oprot.writeString(struct.success);
          oprot.writeFieldEnd();
        }
        oprot.writeFieldStop();
        oprot.writeStructEnd();
      }

    }

    private static class demoSay_resultTupleSchemeFactory implements SchemeFactory {
      public demoSay_resultTupleScheme getScheme() {
        return new demoSay_resultTupleScheme();
      }
    }

    private static class demoSay_resultTupleScheme extends TupleScheme<demoSay_result> {

      
      public void write(org.apache.thrift.protocol.TProtocol prot, demoSay_result struct) throws org.apache.thrift.TException {
        TTupleProtocol oprot = (TTupleProtocol) prot;
        BitSet optionals = new BitSet();
        if (struct.isSetSuccess()) {
          optionals.set(0);
        }
        oprot.writeBitSet(optionals, 1);
        if (struct.isSetSuccess()) {
          oprot.writeString(struct.success);
        }
      }

      
      public void read(org.apache.thrift.protocol.TProtocol prot, demoSay_result struct) throws org.apache.thrift.TException {
        TTupleProtocol iprot = (TTupleProtocol) prot;
        BitSet incoming = iprot.readBitSet(1);
        if (incoming.get(0)) {
          struct.success = iprot.readString();
          struct.setSuccessIsSet(true);
        }
      }
    }

  }

}
