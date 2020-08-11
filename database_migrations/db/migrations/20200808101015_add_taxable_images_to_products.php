<?php
declare(strict_types=1);

use Phinx\Migration\AbstractMigration;

final class AddTaxableImagesToProducts extends AbstractMigration
{

    public function change(): void
    {
        $table = $this->table('products');
        $table->addColumn('thumbnail_url', 'string', ['after' => 'code'])->update();
        $table->addColumn('img_url', 'string', ['after' => 'code'])->update();
        $table->addColumn('taxable', 'boolean')->update();

              
    }
}
