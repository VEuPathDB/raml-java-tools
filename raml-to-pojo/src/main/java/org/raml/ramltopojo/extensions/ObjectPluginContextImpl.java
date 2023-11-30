package org.raml.ramltopojo.extensions;

import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import org.raml.ramltopojo.CreationResult;
import org.raml.ramltopojo.EventType;
import org.raml.ramltopojo.GenerationContext;
import org.raml.ramltopojo.TypeDeclarationType;
import org.raml.v2.api.model.v10.datamodel.TypeDeclaration;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created. There, you have it.
 */
public class ObjectPluginContextImpl implements ObjectPluginContext {
  private final GenerationContext generationContext;
  private final CreationResult result;

  public ObjectPluginContextImpl(GenerationContext generationContext, CreationResult result) {
    this.generationContext = generationContext;
    this.result = result;
  }

  @Override
  public Set<CreationResult> childClasses(String ramlTypeName) {
    return Optional.ofNullable(generationContext.childClasses(ramlTypeName))
      .orElse(Collections.emptySet())
      .stream()
      .map(input -> generationContext.findCreatedType(input, null))
      .collect(Collectors.toSet());
  }

  @Override
  public CreationResult creationResult() {

    return result;
  }

  @Override
  public CreationResult dependentType(TypeDeclaration items) {
    return generationContext.findCreatedType(items.name(), items);
  }

  @Override
  public TypeName forProperty(TypeDeclaration typeDeclaration) {
    return TypeDeclarationType.calculateTypeName("", typeDeclaration, generationContext, EventType.INTERFACE);
  }

  @Override
  public TypeName createSupportClass(TypeSpec.Builder newSupportType) {
    return this.generationContext.createSupportClass(newSupportType);
  }
}
