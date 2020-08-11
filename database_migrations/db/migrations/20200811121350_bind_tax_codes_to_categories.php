<?php
declare(strict_types=1);

use Phinx\Migration\AbstractMigration;

final class BindTaxCodesToCategories extends AbstractMigration
{
    /**
     * Change Method.
     *
     * Write your reversible migrations using this method.
     *
     * More information on writing migrations is available here:
     * https://book.cakephp.org/phinx/0/en/migrations.html#the-change-method
     *
     * Remember to call "create()" or "update()" and NOT "save()" when working
     * with the Table class.
     */
    public function change(): void
    {
        $productTaxCategories= $this->table('shop_product_cateogory_taxes', ['signed' => false]);
        $productTaxCategories->addColumn('shop_category_id', 'biginteger', ['signed' => false])
        ->addColumn('tax_code_id', 'biginteger', ['signed' => false])
        ->addIndex(['shop_category_id', 'tax_code_id'], ['unique' => true])
        ->addForeignKey('shop_category_id', 'shop_belongs_categories', 'id', ['delete'=> 'RESTRICT', 'update'=> 'RESTRICT'])
        ->addForeignKey('tax_code_id', 'shop_tax_code_names', 'id', ['delete'=> 'RESTRICT', 'update'=> 'RESTRICT'])
        ->create();
    }
}
